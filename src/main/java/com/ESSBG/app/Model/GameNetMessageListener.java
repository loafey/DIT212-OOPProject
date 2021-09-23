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
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ConcurrentCircularList<Player> players;
    private ConcurrentCircularList<Integer> joinedUsers;
    private ConcurrentHashMap<Integer, Boolean> confirmedStart;

    protected GameNetMessageListener(LinkedBlockingQueue<JSONObject> msgQueue,
            ConcurrentCircularList<Integer> joinedUsers, ConcurrentCircularList<Player> players,
            ConcurrentHashMap<Integer, Boolean> confirmedStart) {
        this.joinedUsers = joinedUsers;
        this.players = players;
        this.confirmedStart = confirmedStart;
        this.msgQueue = msgQueue;
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
                        joinedUsers.add(id);
                        confirmedStart.put(id, false);
                        players.add(new Player(id, "foo", null));
                    } else {
                        joinedUsers.remove(id);
                        confirmedStart.remove(id);
                        players.remove(id);
                    }
                } else if (reason == "game") {
                    JSONObject data = js.getJSONObject("data");
                    // Confirm
                    // TODO add game logic things here
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
