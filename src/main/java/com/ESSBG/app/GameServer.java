package com.ESSBG.app;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.*;
import java.util.Map.Entry;

import com.ESSBG.app.Model.Game;
import com.ESSBG.app.Network.*;
import org.json.*;

/**
 * @author: Björn Rosengren, Samuel Hammersberg
 *
 * For the game to be able to read messages from the networking interface
 * without blocking the main thread.
 */
public class GameServer implements Runnable {
    private IServer server;
    private LinkedBlockingQueue<JSONObject> msgQueue;
    // Represents their ID + model index.
    private ConcurrentHashMap<Integer, Integer> joinedUsers;
    private ConcurrentHashMap<Integer, Boolean> confirmedStart;
    private Game game;

    public GameServer() {
        this.server = new Server();
        this.joinedUsers = new ConcurrentHashMap<>();
        this.confirmedStart = new ConcurrentHashMap<>();
        game = new Game();
    }

    @Override
    public void run() {
        try {
            server.runServer();
            this.msgQueue = server.getMsgQueue();
        } catch (IOException e) {
        }
        while (!server.isSocketClosed()) {
            gameLogic();
        }
    }

    /**
     * Sends a message to all players. 
     * The message is created from the passed in function, 
     * allowing for unique messages based on player id and player index.
     * @param message
     */
    private void broadcastMessage(BiFunction<? super Integer, ? super Integer, JSONObject> message) {
        joinedUsers.forEach((p, pIndex) -> {
            try {
                boolean result = server.sendData(p, message.apply(p, pIndex));
                if (!result) {
                    System.out.println("ERROR: Failed to send data to user " + p + "!");
                }
            } catch (IOException e) {
            }
        });
    }

    /**
     * Main logic of the game.
     */
    private void gameLogic() {
        // Initial data, will be successfully added throughout the tree of ifs.
        JSONObject js;
        int id;

        // Unpackage payload
        try {
            js = msgQueue.take();
            id = js.getInt("id");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return; // If we can't read message, we don't know what to do. Ignore.
        }

        // Get the reason of the message
        String reason = js.getString("reason");
        if (reason.equals("net")) {
            networkRoutine(id, js);
            return;
        }

        if (reason.equals("game")) {
            // More data!
            JSONObject data = js.getJSONObject("data");
            int msgNum = data.getInt("msgNum");

            if (data.has("start")) {
                ArrayList<Integer> pIDS = new ArrayList<>();
                joinedUsers.forEach((p, pIndex) -> pIDS.add(p));
                game.startGame(pIDS);

                broadcastMessage((p, pIndex) -> new JSONObject("{\"start\": true}"));
                broadcastMessage((p, pIndex) -> game.getPlayerData(pIndex));

                return;
            }

            if (hasAlreadyConfirmedStart(id, msgNum)) {
                return;
            }

            // Shallow validate datapackage.
            if (!data.has("card")) {
                try {
                    server.sendData(id, replyMaker(msgNum, false, "Invalid data package sent!"));
                } catch (NoSuchElementException | IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            try {
                cardLogic(id, msgNum, data.getJSONObject("card"));
            } catch (NoSuchElementException | JSONException | IOException e) {
                e.printStackTrace();
                try {
                    server.sendData(id, replyMaker(msgNum, false, "Invalid card package sent!"));
                } catch (NoSuchElementException | IOException e1) {
                    e1.printStackTrace();
                    // We can't do anything at this point.
                }
            }
        }
    }

    /**
     * CardLogic
     */
    private void cardLogic(int id, int msgNum, JSONObject cardData) throws NoSuchElementException, IOException {
        // Get all the necessary info. Even more data!
        int cardIndex = cardData.getInt("cardIndex");
        String action = cardData.getString("action");

        if (action.equals("discard")) {
            game.trashCard(joinedUsers.get(id), cardIndex);
            server.sendData(id, replyMaker(msgNum, true));
            confirmedStart.replace(id, true);
        } else if (action.equals("monument")) {
            boolean worked = game.upgradeMonument(joinedUsers.get(id), cardIndex);
            if (!worked) {
                server.sendData(id, replyMaker(msgNum, false, "Not enough resources!"));
                return;
            }
            server.sendData(id, replyMaker(msgNum, true));
            confirmedStart.replace(id, true);
        } else if (action.equals("place")) {
            boolean worked = game.pickCard(joinedUsers.get(id), cardIndex);
            if (!worked) {
                server.sendData(id, replyMaker(msgNum, false, "Not enough resources!"));
                return;
            }
            server.sendData(id, replyMaker(msgNum, true));
            confirmedStart.replace(id, true);
        }
        boolean allFinished = true;
        for (Entry<Integer, Boolean> e : confirmedStart.entrySet()) {
            allFinished &= e.getValue();
        }

        if (allFinished) {
            game.tryProgress();

            if (game.getAge() > 3) {
                broadcastMessage((p, pindex) -> {
                    JSONObject data = game.getScoreboard();
                    return data;
                });
                // Stop server
                // Should be done more gracefully perhaps.
                server.stopServer();
            } else {
                game.movePeriodCardsToNextPlayer();
                broadcastMessage((p, pindex) -> {
                    JSONObject data = game.getPlayerData(pindex);
                    return data;
                });

                confirmedStart.forEach((p, _r) -> {
                    confirmedStart.replace(p, false);
                });
            }
        }
    }

    /**
     * Handles networking messages
     *
     * @param id
     * @param js the sent data
     */
    private void networkRoutine(int id, JSONObject js) {
        // Connection. True = Connect, False = Disconnect.
        if (js.getBoolean("data")) {
            System.out.println("Server: " + js);

            joinedUsers.put(id, joinedUsers.size());
            confirmedStart.put(id, false);
        } else {
            // Any connection error should remove.
            joinedUsers.remove(id);
            confirmedStart.remove(id);
        }
    }

    /**
     * If a player has done an action with a card, this player should be locked down
     * until next round.
     */
    private boolean hasAlreadyConfirmedStart(int id, int msgNum) {
        try {
            if (confirmedStart.get(id)) {
                server.sendData(id, replyMaker(msgNum, false, "Already locked in round."));
                return true;
            }
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param msgNum
     * @param reply
     * @param message
     * @return JSONObject with OPTIONAL[message]. Mostly to return why the false
     *         value occurred.
     */
    private JSONObject replyMaker(int msgNum, boolean reply, String message) {
        return new JSONObject().put("msgNum", msgNum).put("reply", reply).put("msg", message);
    }

    private JSONObject replyMaker(int msgNum, boolean reply) {
        return new JSONObject().put("msgNum", msgNum).put("reply", reply);
    }
}
