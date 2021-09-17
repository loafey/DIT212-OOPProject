package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONObject;

public class SocketServer implements Runnable {
    private ServerSocket socket;
    private ConnectedUsers activeUsers = new ConnectedUsers(new ArrayList<Thread>(), new ArrayList<Socket>(),
            Constants.MAXPLAYERS);
    private LinkedBlockingQueue<JSONObject> msgQueue;

    protected SocketServer(ServerSocket socket, LinkedBlockingQueue<JSONObject> msgQueue) {
        this.socket = socket;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Accept is a blocking method/function.
                Socket client = socket.accept();
                Thread t = new Thread(new SocketListener(client.getInputStream(), this.msgQueue));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

