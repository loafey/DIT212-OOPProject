package com.ESSBG.app.Model;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.List;
import java.util.NoSuchElementException;

import com.ESSBG.app.Network.*;
import org.json.*;
import org.lwjgl.system.windows.MSG;

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
        while (true) {
            try {
                JSONObject js = msgQueue.take();
                String reason = js.getString("reason");
                int id = js.getInt("id");
                JSONObject reply = null;
                if (reason == "net") {
                    // Connection. True = Connect, False = Disconnect.
                    if (js.getBoolean("data")) {
                        Player newPlayer = new Player(id, null);
                        joinedUsers.put(id, newPlayer);
                        confirmedStart.put(id, false);
                        players.add(newPlayer);
                    } else {
                        // Any connection error should remove.
                        joinedUsers.remove(id);
                        confirmedStart.remove(id);
                        players.remove(id);
                    }
                } else if (reason == "game") {
                    Player player = joinedUsers.get(id);
                    JSONObject data = js.getJSONObject("data");
                    int msgNum = data.getInt("msgNum");
                    reply = new JSONObject().put("msgNum", msgNum);
                    if (confirmedStart.get(id)) {
                        reply.put("accepted", false).put("msg", "Already locked in round.");
                        server.sendData(id, reply);
                        continue;
                    }
                    // Change name routine
                    if (data.has("name")) {
                        if (playerNameChange(id, data.getString("name"))) {
                            server.sendData(id, reply.put("accepted", true));
                            continue;
                        } else {
                            reply.put("accepted", false).put("msg", "Name already taken.");
                            server.sendData(id, reply);
                            continue;
                        }
                    }

                    if (data.has("card")) {
                        JSONObject cardData = data.getJSONObject("card");
                        int cardIndex = cardData.getInt("cardIndex");
                        String action = cardData.getString("action");

                        // Check if index is allowed.
                        if (0 < cardIndex || cardIndex >= player.getCardList().size()) {
                            server.sendData(id, reply.put("accepted", false).put("msg", "Select a valid card!"));
                            continue;
                        }

                        // throw, place, monument
                        // We have to check whether player has enough resources
                        // to upgrade monument or buy card.
                        if (action.equals("monument") || action.equals("place")) {
                            AbstractCard selectedCard = player.getCardList().get(cardIndex);
                            Player leftNeighbor = players.getPrevious(player);
                            Player rightNeighbor = players.getNext(player);

                            // TODO remove placeholder for real method
                            boolean ok_buy_card = true;
                            // Check if player can buy this card.

                            if (!ok_buy_card) {
                                server.sendData(id, reply.put("accepted", false));
                                continue;
                            }
                            server.sendData(id, reply.put("accepted", true));

                            // Confirm user to this round.
                            confirmedStart.put(id, true);

                            // Delete players resources
                            return;
                        }
                    }

                    return;
                }
            } catch (InterruptedException | NoSuchElementException | JSONException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean playerNameChange(int id, String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return false;
            }
        }
        players.get(id).setName(name);
        return true;
    }
}
