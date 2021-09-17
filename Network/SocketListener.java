package com.ESSBG.app.Network;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;

import com.badlogic.gdx.utils.Json;

import org.json.*;

public class SocketListener implements Runnable {
    private InputStream client;
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ArrayList<Thread> threadList;
    private Lock lock;

    public SocketListener(InputStream client, Lock lock, ArrayList<Thread> threadList,
            LinkedBlockingQueue<JSONObject> msgQueue) {
        this.client = client;
        this.lock = lock;
        this.threadList = threadList;
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
            if (!connectHandshake()) {
                return;
            }

            lock.lock();
            if (threadList.size() >= Constants.MAXPLAYERS) {
                lock.unlock();
                return;
            }
            threadList.add(this);
            int id = threadList.indexOf(this); // Preliminary id, might change during runtime.
            lock.unlock();
            msgQueue.add(new JSONObject(
                    String.format("{\"reason\":\"net\", \"data\":\"{\"action\":\"connect\", \"id\": %o}\"}", id)));

            while (true) {
                JSONObject recvData = recvAll();
                // Disconnect or any networking error.
                if (recvData == null) {
                    lock.lock();
                    id = threadList.indexOf(this);
                    threadList.remove(this);
                    lock.unlock();
                    msgQueue.add(new JSONObject(String
                            .format("{\"reason\":\"net\", \"data\":\"{\"action\":\"disconnect\", \"id\": %o}\"}", id)));
                    return;
                }
                msgQueue.add(recvData);
            }

        } catch (

        IOException e) {
            e.printStackTrace();
        }

    }

    private boolean connectHandshake() {
        // First get initial data, should be an net action.
        JSONObject js = recvAll();
        if (js != null) {
            // Fails if client doesn't have correct connection handshake
            if ((String) js.get("action").equals("net")) {
                if ((String) js.get("data").equals("connect")) {
                    return true;
                }
            }
        }
        return false;
    }

    private int byteArrayToInt(byte[] b) {
        return (b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24);
    }
}
