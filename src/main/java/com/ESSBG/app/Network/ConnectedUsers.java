package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;

public class ConnectedUsers {
    private Thread[] threadList;
    private Socket[] socketList;
    private int currentUsers = 0;
    private int maxPlayers;

    protected ConnectedUsers(Thread[] threadList, Socket[] socketList, int maxPlayers) {
        this.threadList = threadList;
        this.socketList = socketList;
        this.maxPlayers = maxPlayers;
    }

    protected void addUser(Thread t, Socket s) {
        
    }
}
