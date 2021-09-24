package com.ESSBG.app.Model;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.List;
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
                if (reason == "net") {
                    // Connection. True = Connect, False = Disconnect.
                    if (js.getBoolean("data")) {
                        Player newPlayer = new Player(id, "foo", null);
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
                    // Change name routine
                    if (data.has("name")) {
                        playerNameChange(id, data.getString("name"));
                        continue;
                    }

                    if (data.has("card")) {
                        JSONObject cardData = data.getJSONObject("card");
                        int cardIndex = cardData.getInt("cardIndex");
                        String action = cardData.getString("action");

                        // Check if index is allowed.
                        if (0 < cardIndex || cardIndex >= player.getCardList().size()) {
                            throw new IllegalArgumentException("Index out of range");
                        }

                        // throw, place, monument
                        // We have to check whether player has enough resources
                        // to upgrade monument or buy card.
                        if (action.equals("monument") || action.equals("place")) {
                            // TODO check if user is allowed for these routines.
                            return;
                        }
                    }

                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void playerNameChange(int id, String name) {
        players.get(id).setName(name);
    }
}
