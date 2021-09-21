package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class Client extends Base implements IClient {
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private Socket serverSocket;
    private Thread thread;
    private Lock lock;

    @Override
    public boolean runClient() {
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
            serverSocket = new Socket(InetAddress.getByName(Constants.IP), Constants.PORT);
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

    // Only for testing!
    protected boolean isListenerRunning() {
        return thread.isAlive();
    }
}