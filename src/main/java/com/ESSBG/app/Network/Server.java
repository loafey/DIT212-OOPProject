package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

public class Server extends Base implements IServer {
    private ConcurrentHashMap<Integer, Socket> hashMap = new ConcurrentHashMap<Integer, Socket>();
    private LinkedBlockingQueue<JSONObject> msgQueue = new LinkedBlockingQueue<JSONObject>();
    private ServerSocket socket;
    private volatile int[] maxplayers = { Constants.MAXPLAYERS };

    public boolean initServer() {
        try {
            this.socket = new ServerSocket(Constants.PORT);
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
}
