package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class Client extends Base implements IClient {
    private LinkedBlockingQueue<JSONObject> msgQueue = new LinkedBlockingQueue<JSONObject>();
    private Thread thread;
    private Socket serverSocket;
    private Lock lock = new ReentrantLock(true);

    @Override
    public boolean initClient() {
        try {
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
            thread = new Thread(new SocketClientListener(serverSocket, lock, msgQueue));
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
        return this.msgQueue;
    }

    @Override
    public void stopClient() {
        try {
            serverSocket.close();
        } catch (Exception e) {
        }
    }
}
