package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;

import java.io.*;

public class AcceptSockets implements Runnable {
    private ServerSocket socket;
    private ArrayList<Socket> socketList = new ArrayList<Socket>();
    private ArrayList<SocketThread> threadList;

    public AcceptSockets(ServerSocket socket, ArrayList<SocketThread> threadList) {
        this.socket = socket;
        this.threadList = threadList;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket client = socket.accept();
                this.socketList.add(client);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
