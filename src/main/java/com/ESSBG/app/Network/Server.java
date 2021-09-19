package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

public class Server extends Base implements IServer {
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ServerSocket socket;
    // This is to use first index as a remote controller of socketlistener.
    private volatile int[] maxplayers = { Constants.MAXPLAYERS };

    public boolean initServer() {
        try {
            msgQueue = new LinkedBlockingQueue<JSONObject>();
            hashMap = new ConcurrentHashMap<Integer, Socket>();
            socket = new ServerSocket(Constants.PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * Runs the server at specified port in constants.java
     */
    @Override
    public void runServer() {
        (new Thread(new SocketServer(socket, hashMap, msgQueue, maxplayers))).start();
    }

    @Override
    public boolean sendData(int id, JSONObject jsonobj) throws UnsupportedEncodingException, Exception {
        Socket clientSocket = hashMap.get(id);
        // Check whether player values are correct.
        if (clientSocket == null) {
            throw new Exception("User should already be deleted from server");
        }
        return naiveSendData(clientSocket, jsonobj);
    }

    @Override
    public void startGame() {
        maxplayers[0] = hashMap.size();
    }

    @Override
    public LinkedBlockingQueue<JSONObject> getMsgQueue() {
        return this.msgQueue;
    }

    @Override
    public void stopServer() {
        hashMap.values().forEach(socket -> {
            try {
                socket.close();
            } catch (Exception e) {
            }
        });
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    // Only for testing
    protected boolean hasUserJoined(int id) {
        return hashMap.containsKey(id);
    }

    // Only for testing or not. Disconnects the user.
    protected void disconnectUserSocket(int id) throws IOException {
        hashMap.get(id).close();
    }

    protected int getNumberOfUsers() {
        return hashMap.size();
    }
}