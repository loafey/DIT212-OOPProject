package com.ESSBG.app.Network;

import java.net.*;
import java.util.ArrayList;
import com.ESSBG.app.Network.AcceptSockets;

import java.io.*;

public class Server implements IServer, Constants {
    private ArrayList<SocketThread> threadList = new ArrayList<SocketThread>();
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
        this.acceptSocketThread = new Thread(new AcceptSockets(this.socket, threadList));
        this.acceptSocketThread.start();
    }

    @Override
    public boolean sendData() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getData() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean stopServer() {
        // TODO Auto-generated method stub
        return false;
    }





}
