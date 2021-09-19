package com.ESSBG.app.Network;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import org.json.JSONObject;

/***
 * This class is only for detaching itself from the main-thread and letting it
 * run just serving new players to connect and "attach" a server for the player.
 */
public class SocketServer implements Runnable {
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private ServerSocket socket;
    private int[] maxplayers;
    private Lock lock = new ReentrantLock(true);

    protected SocketServer(ServerSocket socket, ConcurrentHashMap<Integer, Socket> hashMap,
            LinkedBlockingQueue<JSONObject> msgQueue, int[] maxplayers) {
        this.socket = socket;
        this.hashMap = hashMap;
        this.maxplayers = maxplayers;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Accept is a blocking method/function. Wraps the socket in a thread and
                // dispatches it.
                Socket client = socket.accept();
                Thread t = new Thread(new SocketServerListener(client, lock, hashMap, msgQueue, maxplayers));
                t.start();
            }
        } catch (IOException dont_care) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}