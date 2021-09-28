package com.ESSBG.app.Model;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Network.*;
import org.json.*;

/**
 * For the game to be able to read messages from the networking interface
 * without blocking the main thread.
 */
public class GameNetMessageListener implements Runnable {
    private IServer server;
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ConcurrentCircularList<Player> players;
    private ConcurrentHashMap<Integer, Player> joinedUsers;
    private ConcurrentHashMap<Integer, Boolean> confirmedStart;

    protected GameNetMessageListener(IServer server, ConcurrentHashMap<Integer, Player> joinedUsers,
            ConcurrentCircularList<Player> players, ConcurrentHashMap<Integer, Boolean> confirmedStart) {
        this.server = server;
        this.joinedUsers = joinedUsers;
        this.players = players;
        this.confirmedStart = confirmedStart;
        this.msgQueue = server.getMsgQueue();
    }

    @Override
    public void run() {
        while (!server.isSocketClosed()) {
            gameLogic();
        }
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

            // Change name routine
            if (data.has("name")) {
                nameChangeRoutine(id, msgNum, data.getString("name"));
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
        Player player = joinedUsers.get(id);
        // TODO
        //List<Card> cardList = player.getCardList();

        // TODO
        // Check if index is allowed.
        // if (0 < cardIndex || cardIndex >= player.getCardList().size()) {
        //     server.sendData(id, replyMaker(msgNum, false, "Select a valid card!"));
        //     return;
        // }

        // discard, place, monument
        // We have to check whether player has enough resources
        // to upgrade monument or buy card.
        if (action.equals("discard")) {
            // TODO
            //discardAction(id, msgNum, cardList, cardIndex);
            return;
        }

        if (action.equals("monument") || action.equals("place")) {
            // Dataspree!
            // TODO
            //Card selectedCard = player.getCardList().get(cardIndex);
            Player leftNeighbor = players.getPrevious(player);
            Player rightNeighbor = players.getNext(player);

            // TODO remove placeholder for real method
            boolean ok_buy_card = true;
            // Check if player can buy this card.

            if (!ok_buy_card) {
                server.sendData(id, replyMaker(msgNum, false, "Not enough resources!"));
                return;
            }
            server.sendData(id, replyMaker(msgNum, true));

            // Confirm user to this round.
            confirmedStart.put(id, true);

            // Delete players resources
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
            // TODO
            //Player newPlayer = new Player(id);
            //joinedUsers.put(id, newPlayer);
            //confirmedStart.put(id, false);
           //players.add(newPlayer);
        } else {
            // Any connection error should remove.
            joinedUsers.remove(id);
            confirmedStart.remove(id);
            players.remove(id);
        }
    }

    /**
     * Changes name of said player if the name doesn't already exist.
     */
    private void nameChangeRoutine(int id, int msgNum, String name) {
        try {
            if (playerNameChange(id, name)) {
                server.sendData(id, replyMaker(msgNum, true));
            } else {
                server.sendData(id, replyMaker(msgNum, false, "Name already taken."));
            }
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
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
     * logic what to do if user want to discard the card.
     */
    private void discardAction(int playerID, int msgNum, List<Card> cardList, int cardIndex) {
        // 2 Coins, and for each yellow 1 coin..
        cardList.remove(cardIndex);
        int nickelBack = 2;
        for (Card c : cardList) {
            if (c.getColor() == ColorEnum.YELLOW) {
                nickelBack++;
            }
        }
        // TODO
        //joinedUsers.get(playerID).addCoins(nickelBack);
        try {
            server.sendData(playerID, replyMaker(msgNum, true));
            confirmedStart.put(playerID, true);
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
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

    private boolean playerNameChange(int id, String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return false;
            }
        }
        // TODO
        //players.get(id).setName(name);
        return true;
    }
}
