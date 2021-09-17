package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;

import java.io.*;

public class Server implements IServer, Constants {
    private ConnectedUsers activeUsers = new ConnectedUsers(new Thread[Constants.MAXPLAYERS],
            new Socket[Constants.MAXPLAYERS], Constants.MAXPLAYERS);
    private ConcurrentLinkedQueue<JSONObject> msgQueue = new ConcurrentLinkedQueue<JSONObject>();
    private Thread acceptSocketThread;
    private ServerSocket socket;

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
    @Override
    public void runServer() {
        this.acceptSocketThread = new Thread(
                new AcceptSockets(this.socket, activeUsers, this.msgQueue));
        this.acceptSocketThread.start();
    }

    // Index in threadlist is equal to the player in the game.
    @Override
    public boolean sendData(int player, JSONObject jsonobj) {
        // Check whether player values are correct. Naive send json atm.
        if (0 < player || player > socketList.size()) {
            return false;
        }

        // Send the data
        try {
            byte[] b = jsonobj.toString().getBytes("utf-8");
            int msgLen = b.length;
            OutputStream client = socketList.get(player).getOutputStream();
            client.write(intToByteArray(msgLen));
            client.write(b);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ConcurrentLinkedQueue<JSONObject> getData() {
        if (msgQueue.isEmpty()) {
            return null;
        }
        return this.msgQueue;
    }

    @Override
    public boolean stopServer() {
        // TODO Auto-generated method stub
        return false;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF) };
    }
}
