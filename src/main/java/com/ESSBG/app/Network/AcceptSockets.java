package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.JSONObject;

import java.io.*;

public class AcceptSockets implements Runnable {
    private ServerSocket socket;
    private ArrayList<Socket> socketList;
    private ArrayList<Thread> threadList;
    private ConcurrentLinkedQueue<JSONObject> msgQueue;

    public AcceptSockets(ServerSocket socket, ArrayList<Thread> threadList, ArrayList<Socket> socketList,
            ConcurrentLinkedQueue<JSONObject> msgQueue) {
        this.socket = socket;
        this.threadList = threadList;
        this.socketList = socketList;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Accept is a blocking method/function.
                Socket client = socket.accept();
                this.socketList.add(client);
                Thread t = new Thread(new SocketListener(client.getInputStream(), this.msgQueue));
                threadList.add(t);
                t.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
