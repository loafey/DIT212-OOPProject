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
    private volatile int[] maxplayersAtIndexZero = { Constants.MAXPLAYERS };

    /***
     * Runs the server at specified port in constants.java
     */
    @Override
    public boolean runServer() {
        try {
            // Shut down existing sockets
            try {
                hashMap.values().forEach(sock -> {
                    try {
                        sock.close();
                    } catch (Exception ignore) {
                    }
                });
                socket.close();
            } catch (Exception e) {
            }
            // Reset "world"
            maxplayersAtIndexZero[0] = Constants.MAXPLAYERS;
            msgQueue = new LinkedBlockingQueue<JSONObject>();
            hashMap = new ConcurrentHashMap<Integer, Socket>();
            socket = new ServerSocket(Constants.PORT);
            (new Thread(new SocketServer(socket, hashMap, msgQueue, maxplayersAtIndexZero))).start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendData(int id, JSONObject jsonobj) throws UnsupportedEncodingException, Exception {
        if (socket == null || socket.isClosed()) {
            return false;
        }
        Socket clientSocket = hashMap.get(id);
        // Check whether player values are correct.
        if (clientSocket == null) {
            throw new Exception("User should already be deleted from server");
        }
        return naiveSendData(clientSocket, jsonobj);
    }

    @Override
    public void startGame() {
        maxplayersAtIndexZero[0] = hashMap.size();
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