package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class Client extends Base implements IClient {
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private Socket serverSocket;
    private Thread thread;
    private Lock lock = new ReentrantLock(true);

    @Override
    public boolean initClient() {
        try {
            msgQueue = new LinkedBlockingQueue<JSONObject>();
            serverSocket = new Socket(InetAddress.getByName(Constants.IP), Constants.PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void runClient() {
        try {
            SocketClientListener a = new SocketClientListener(serverSocket, lock, msgQueue);
            thread = new Thread(a);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendData(JSONObject jsonobj) throws UnsupportedEncodingException {
        return naiveSendData(serverSocket, jsonobj);
    }

    @Override
    public LinkedBlockingQueue<JSONObject> getMsgQueue() {
        return msgQueue;
    }

    @Override
    public void stopClient() {
        try {
            serverSocket.close();
        } catch (Exception e) {
        }
    }

    // Only for testing!
    protected boolean isListenerRunning() {
        return thread.isAlive();
    }
}