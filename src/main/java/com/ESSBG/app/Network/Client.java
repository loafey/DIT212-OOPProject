package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class Client implements IClient {
    private LinkedBlockingQueue<JSONObject> msgQueue = new LinkedBlockingQueue<JSONObject>();
    private Thread thread;
    private Socket socket;
    private Lock lock = new ReentrantLock(true);

    public boolean initClient() {
        try {
            socket = new Socket(InetAddress.getByName(Constants.IP), Constants.PORT);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void runClient() {
        try {
            thread = new Thread(new SocketClientListener(socket, lock, msgQueue));
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendData(JSONObject jsonobj) {
        // Send the data
        try {
            byte[] b = jsonobj.toString().getBytes(Constants.encoding);
            int msgLen = b.length;
            OutputStream server = socket.getOutputStream();
            server.write(Converter.intToByteArray(msgLen));
            server.write(b);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LinkedBlockingQueue<JSONObject> getData() {
        return this.msgQueue;
    }

    @Override
    public void stopClient() {
        try {
            socket.close();
        } catch (Exception e) {
        }
    }
}
