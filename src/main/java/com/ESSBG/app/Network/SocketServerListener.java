package com.ESSBG.app.Network;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * Author: Björn Rosengren
 *
 * "Attaches" itself to each client to be able to listen what the client are
 * telling us. If we didn't have threading, everything would be blocked due to
 * sockets are blocking. Async would work but then there would be a risk of
 * having a while loop running wild, 100% cpu...
 */
public class SocketServerListener extends SocketBaseListener {
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private volatile int[] maxplayersAtIndexZero;
    private int id = this.hashCode();

    protected SocketServerListener(Socket socket, Lock lock, ConcurrentHashMap<Integer, Socket> hashMap,
            LinkedBlockingQueue<HashMapWithTypes> msgQueue, int[] maxplayersAtIndexZero) {
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
        msgQueue.add(ReplyFactory.getNetworkWithID(id, true));

        while (receiveDataPushToQueue()) {
        }
    }

    private boolean receiveDataPushToQueue() {
        HashMapWithTypes recvData = recvAll();
        // Disconnect or any networking error.
        if (recvData == null) {
            goodByeWorld();
            // Tell listener that a socket has been disconnected
            // and that we are dying. Send a last message.
            msgQueue.add(ReplyFactory.getNetworkWithID(id, false));
            return false;
        }
        msgQueue.add(ReplyFactory.getGameWithID(id, recvData));
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
        HashMapWithTypes data = recvAll();
        if (data != null) {
            // Fails if client doesn't have correct connection handshake
            try {
                if (data.getString("reason").equals("net") && data.getBoolean("data")) {
                    return true;
                }
            } catch (Exception ignore_but_warn_that_user_failed_handshake) {
                System.out.println("> User failed handshake. Probably wrong datastructure.");
            }
        }
        return false;
    }
}