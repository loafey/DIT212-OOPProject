package com.ESSBG.app.Network;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.*;

public class SocketListener implements Runnable {
    private InputStream client;
    private ConcurrentLinkedQueue<JSONObject> msgQueue;

    public SocketListener(InputStream client, ConcurrentLinkedQueue<JSONObject> msgQueue) {
        this.client = client;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int msgLen = byteArrayToInt(client.readNBytes(2));
                byte[] buffer = new byte[4096];
                client.read(buffer, 0, msgLen);
                msgQueue.add(new JSONObject(buffer));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int byteArrayToInt(byte[] b) {
        return (b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24);
    }
}
