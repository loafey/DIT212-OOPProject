package com.ESSBG.app.Network;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;

public class SocketServerListener extends SocketBaseListener {

    private ConcurrentHashMap<Integer, Socket> hashMap;
    private JSONObject netAction;
    private int[] maxplayers;

    public SocketServerListener(Socket socket, Lock lock, ConcurrentHashMap<Integer, Socket> hashMap,
            LinkedBlockingQueue<JSONObject> msgQueue, int[] maxplayers) {
        super(socket, lock, msgQueue);
        this.hashMap = hashMap;
        this.maxplayers = maxplayers;
        this.netAction = new JSONObject(Constants.netAction);
        this.netAction.put("id", this.hashCode());
    }

    @Override
    public void run() {
        if (!connectHandshake()) {
            disconnectSocket();
            return;
        }

        // Check max sockets. If size is equal to max => Stop execute and kill the conn.
        lock.lock();
        if (numberOfActiveThreads >= maxplayers[0]) {
            disconnectSocket();
            lock.unlock();
            // Let thread die without anyone knowing through the hashmap.
            return;
        }
        hashMap.put(this.hashCode(), socket);
        numberOfActiveThreads++;
        lock.unlock();
        msgQueue.add(netAction.put("reason", "net").getJSONObject("data").put("action", true));

        while (receiveDataPushToQueue()) {
        }
    }

    private boolean receiveDataPushToQueue() {
        JSONObject recvData = recvAll();
        // Disconnect or any networking error.
        if (recvData == null || (recvData.getString("reason").equals("net")
                && !recvData.getJSONObject("data").getBoolean("action"))) {

            goodByeWorld();
            // Tell listener that a socket has been disconnected
            // and that we are dying. Send a last message.
            msgQueue.add(netAction.put("reason", "net").getJSONObject("data").put("action", false));
            return false;
        }
        msgQueue.add(netAction.put("reason", "game").put("data", recvData));
        return true;
    }

    private void goodByeWorld() {
        // Important stuff first, then disconnect since we want the rest of the
        // domain to continue without as little delay as possible.
        lock.lock();
        hashMap.remove(this.hashCode());
        numberOfActiveThreads--;
        lock.unlock();
        disconnectSocket();
    }

    private boolean connectHandshake() {
        // First get initial data, should be an net action.
        JSONObject js = recvAll();
        if (js != null) {
            // Fails if client doesn't have correct connection handshake
            try {
                if (js.get("reason").equals("net") && js.getJSONObject("data").getBoolean("action")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
