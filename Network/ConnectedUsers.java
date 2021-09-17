package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;

public class ConnectedUsers {
    private ArrayList<Thread> threadList;
    private ArrayList<Socket> socketList;
    private int currentUsers = 0;
    private int maxPlayers;

    protected ConnectedUsers(ArrayList<Thread> threadList, ArrayList<Socket> socketList, int maxPlayers) {
        this.threadList = threadList;
        this.socketList = socketList;
        this.maxPlayers = maxPlayers;
    }

    protected void addUser(Thread t, Socket s) {
        return;
    }
}
