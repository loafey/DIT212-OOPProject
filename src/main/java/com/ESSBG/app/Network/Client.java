package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

/**
 * Author: Björn Rosengren
 *
 * The client class. This client connects to the server and if it is connected,
 * it will detach the listener part due to its blocking nature and listen to
 * each message the server sends.
 */
public class Client extends Base implements IClient {
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private Socket serverSocket;
    private Thread thread;
    private Lock lock;

    @Override
    public boolean runClient(String ipAddress) {
        return runClientHelper(ipAddress);
    }

    @Override
    public boolean runClient() {
        return runClientHelper(Constants.IP);
    }

    private boolean runClientHelper(String ipAddress) {
        try {
            // Shutdown existing stuff
            try {
                serverSocket.close();
                Thread.sleep(20);
            } catch (Exception ignore_since_we_forcefully_close_socket) {
            }
            // Recreate the world
            lock = new ReentrantLock(true);
            msgQueue = new LinkedBlockingQueue<JSONObject>();
            serverSocket = new Socket(InetAddress.getByName(ipAddress), Constants.PORT);
            thread = new Thread(new SocketClientListener(serverSocket, lock, msgQueue));
            thread.start();
            return true;
        } catch (Exception ignore_since_we_cant_reconnect_to_server_anyways) {
        }
        return false;
    }

    @Override
    public boolean sendData(JSONObject jsonobj) {
        if (serverSocket == null || serverSocket.isClosed()) {
            return false;
        }
        try {
            super.sendData(serverSocket, jsonobj);
            return true;
        } catch (IOException ignore_since_false_is_the_same) {
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedBlockingQueue<JSONObject> getMsgQueue() {
        return msgQueue;
    }

    @Override
    public void stopClient() {
        try {
            serverSocket.close();
        } catch (Exception e) {
        }
    }

    // Read last multiline comment at the bottom in Server.java
    // Only for testing!
    protected boolean isListenerRunning() {
        return thread.isAlive();
    }
}