package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.JSONObject;

public class AcceptSockets implements Runnable {
    private ServerSocket socket;
    private ConnectedUsers activeUsers;
    private ConcurrentLinkedQueue<JSONObject> msgQueue;

    protected AcceptSockets(ServerSocket socket, ConnectedUsers activeUsers,
            ConcurrentLinkedQueue<JSONObject> msgQueue) {
        this.socket = socket;
        this.activeUsers = activeUsers;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Accept is a blocking method/function.
                Socket client = socket.accept();
                if ()
                Thread t = new Thread(new SocketListener(client.getInputStream(), this.msgQueue));
                socketList.add(client);
                threadList.add(t);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
