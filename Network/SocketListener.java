package com.ESSBG.app.Network;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.utils.Json;

import org.json.*;

public class SocketListener implements Runnable {
    private InputStream client;
    private LinkedBlockingQueue<JSONObject> msgQueue;

    public SocketListener(InputStream client, LinkedBlockingQueue<JSONObject> msgQueue) {
        this.client = client;
        this.msgQueue = msgQueue;
    }

    private JSONObject recvAll() {
        try {
            int msgLen = byteArrayToInt(client.readNBytes(2));
            byte[] buffer = new byte[4096];
            client.read(buffer, 0, msgLen);
            return new JSONObject(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            // First get initial data, should be an net action.
            JSONObject js = recvAll();
            if (js)
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
