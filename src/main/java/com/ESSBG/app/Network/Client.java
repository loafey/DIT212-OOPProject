package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.concurrent.*;

import java.io.*;

public class Client implements IClient {
    private ConcurrentLinkedQueue<JSONObject> msgQueue = new ConcurrentLinkedQueue<JSONObject>();
    private Thread thread;
    private Socket socket;

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
            thread = new Thread(new SocketListener(socket.getInputStream(), msgQueue));
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Override
    public boolean sendData(JSONObject jsonobj) {
        // Send the data
        try {
            byte[] b = jsonobj.toString().getBytes("utf-8");
            int msgLen = b.length;
            OutputStream server = socket.getOutputStream();
            server.write(intToByteArray(msgLen));
            server.write(b);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // @Override
    public ConcurrentLinkedQueue<JSONObject> getData() {
        if (msgQueue.isEmpty()) {
            return null;
        }
        return this.msgQueue;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF) };
    }

    @Override
    public boolean stopClient() {
        // TODO Auto-generated method stub
        return false;
    }
}
