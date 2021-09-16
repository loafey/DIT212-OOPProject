package com.ESSBG.app.Network;

import java.util.HashMap;

public class SocketThread implements Runnable {
    public volatile boolean gameStarted = false;
    private Socket client;
    HashMap<String, String> map = new HashMap<String, String>();

    public SocketThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while(true) {

        }

    }

}
