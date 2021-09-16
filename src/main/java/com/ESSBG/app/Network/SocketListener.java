package com.ESSBG.app.Network;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.*;

public class SocketListener implements Runnable {
    private InputStream client;
    HashMap<String, String> map = new HashMap<String, String>();
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

    public static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF) };
    }

}
