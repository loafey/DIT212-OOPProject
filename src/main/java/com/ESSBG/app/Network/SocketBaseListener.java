package com.ESSBG.app.Network;

import java.net.*;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;

import org.json.*;

public abstract class SocketBaseListener implements Runnable {
    protected Socket socket;
    protected LinkedBlockingQueue<JSONObject> msgQueue;
    protected Lock lock;
    protected int numberOfActiveThreads = 0;

    protected SocketBaseListener(Socket socket, Lock lock, LinkedBlockingQueue<JSONObject> msgQueue) {
        this.socket = socket;
        this.lock = lock;
        this.msgQueue = msgQueue;
    }

    protected JSONObject recvAll() {
        try {
            InputStream stream = socket.getInputStream();
            int msgLen = Converter.byteArrayToInt(stream.readNBytes(2));
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            stream.read(buffer, 0, msgLen);
            return new JSONObject(buffer);
        } catch (IOException e) {
            // Don't care, if server kicks the player etc...
        }
        return null;
    }

    protected void disconnectSocket() {
        try {
            socket.close();
        } catch (Exception ignore_because_user_is_not_welcome) {
        }
    }
}
