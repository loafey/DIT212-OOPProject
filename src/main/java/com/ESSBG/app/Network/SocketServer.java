package com.ESSBG.app.Network;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Author: Björn Rosengren
 *
 * This class is only for detaching itself from the main-thread and letting it
 * run just serving new players to connect and "attach" a server for the player.
 */
public class SocketServer implements Runnable {
    private LinkedBlockingQueue<HashMapWithTypes> msgQueue;
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private ServerSocket socket;
    private volatile int[] maxplayersAtIndexZero;
    private Lock lock = new ReentrantLock(true);

    protected SocketServer(ServerSocket socket, ConcurrentHashMap<Integer, Socket> hashMap,
            LinkedBlockingQueue<HashMapWithTypes> msgQueue, int[] maxplayersAtIndexZero) {
        this.socket = socket;
        this.hashMap = hashMap;
        this.maxplayersAtIndexZero = maxplayersAtIndexZero;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Accept is a blocking method/function. Wraps the socket in a thread and
                // dispatches it.
                Socket client = socket.accept();
                client.setSoTimeout(4 * 1000);
                Thread t = new Thread(new SocketServerListener(client, lock, hashMap, msgQueue, maxplayersAtIndexZero));
                t.start();
            }
        } catch (Exception ignore_since_we_dont_care_about_sleepy_threads) {
            // Also ignore that we close the socket.
        }
    }
}