package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

public class Server implements IServer {
    private ConcurrentHashMap<Integer, Socket> hashMap = new ConcurrentHashMap<Integer, Socket>();
    private LinkedBlockingQueue<JSONObject> msgQueue = new LinkedBlockingQueue<JSONObject>();
    private ServerSocket socket;
    private int[] maxplayers = { Constants.MAXPLAYERS };

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
     * Runs the server at specified port in constants.java.
     */
    // @Override
    public void runServer() {
        (new Thread(new SocketServer(socket, hashMap, msgQueue, maxplayers))).start();
    }

    // Index in threadlist is equal to the player in the game.
    // @Override
    public boolean sendData(int player, JSONObject jsonobj) throws Exception {
        // Check whether player values are correct.
        if (hashMap.get(player) == null) {
            // netAction.put("action", false);
            // msgQueue.add(netAction.put("action", false));
            throw new Exception("User should already be deleted from listener");
        }

        // Send the data
        byte[] b = jsonobj.toString().getBytes(Constants.encoding);
        int msgLen = b.length;
        for (Socket clientSocket : hashMap.values()) {
            try {
                OutputStream clientStream = clientSocket.getOutputStream();
                clientStream.write(Converter.intToByteArray(msgLen));
                clientStream.write(b);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
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
        try {
            socket.close();
        } catch (Exception e) {
        }
        hashMap.values().forEach(socket -> {
            try {
                socket.close();
            } catch (Exception e) {
            }
        });
    }
}
