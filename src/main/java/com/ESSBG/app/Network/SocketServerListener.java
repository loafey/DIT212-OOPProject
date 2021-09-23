package com.ESSBG.app.Network;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;

public class SocketServerListener extends SocketBaseListener {
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private volatile int[] maxplayersAtIndexZero;
    private int id = this.hashCode();

    protected SocketServerListener(Socket socket, Lock lock, ConcurrentHashMap<Integer, Socket> hashMap,
            LinkedBlockingQueue<JSONObject> msgQueue, int[] maxplayersAtIndexZero) {
        super(socket, lock, msgQueue);
        this.hashMap = hashMap;
        this.maxplayersAtIndexZero = maxplayersAtIndexZero;
    }

    @Override
    public void run() {
        // Check if user has correct handshake procedure.
        if (!connectHandshake()) {
            disconnectSocket();
            return;
        }

        // Check max sockets. If size is equal to max => Stop execute and kill the conn.
        lock.lock();
        if (numberOfActiveThreads >= maxplayersAtIndexZero[0]) {
            disconnectSocket();
            lock.unlock();
            // Let thread die without anyone knowing through the hashmap.
            return;
        }
        hashMap.put(this.hashCode(), socket);
        numberOfActiveThreads++;
        lock.unlock();
        msgQueue.add(JSONFactory.getNetworkWithID(id, true));

        while (receiveDataPushToQueue()) {
        }
    }

    private boolean receiveDataPushToQueue() {
        JSONObject recvData = recvAll();
        // Disconnect or any networking error.
        if (recvData == null) {
            goodByeWorld();
            // Tell listener that a socket has been disconnected
            // and that we are dying. Send a last message.
            msgQueue.add(JSONFactory.getNetworkWithID(id, false));
            return false;
        }
        msgQueue.add(JSONFactory.getGameWithID(id, recvData));
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
                if (js.getString("reason").equals("net") && js.getBoolean("data")) {
                    return true;
                }
            } catch (Exception ignore_but_warn_that_user_failed_handshake) {
                System.out.println("> User failed handshake. Probably wrong datastructure.");
            }
        }
        return false;
    }
}