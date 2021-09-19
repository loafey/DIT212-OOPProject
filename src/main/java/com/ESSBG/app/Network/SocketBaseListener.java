package com.ESSBG.app.Network;

import java.net.*;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import org.json.*;

/**
 * Baseclass to reduce copy paste, easier testing.
 */
public abstract class SocketBaseListener implements Runnable {
    protected Socket socket;
    protected LinkedBlockingQueue<JSONObject> msgQueue;
    protected Lock lock;
    protected static volatile int numberOfActiveThreads = 0;

    protected SocketBaseListener(Socket socket, Lock lock, LinkedBlockingQueue<JSONObject> msgQueue) {
        this.socket = socket;
        this.lock = lock;
        this.msgQueue = msgQueue;
    }

    protected JSONObject recvAll() {
        try {
            byte[] headerLenByte = new byte[Constants.HEADERLEN];
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            InputStream stream = socket.getInputStream();

            // Get length of message
            stream.read(headerLenByte, 0, Constants.HEADERLEN);
            int msgLen = Converter.byteArrayToInt(headerLenByte);
            if (msgLen == 0) {
                return null;
            }
            // Read the message and then pick the data we want.
            stream.read(buffer, 0, msgLen);
            byte[] reduced = new byte[msgLen];
            for (int i = 0; i < msgLen; i++) {
                reduced[i] = buffer[i];
            }
            return new JSONObject(new String(reduced));
        } catch (IOException e) {
            // Don't care, if server kicks the player etc...
            // Also how dare them to send me invalid data?!
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
