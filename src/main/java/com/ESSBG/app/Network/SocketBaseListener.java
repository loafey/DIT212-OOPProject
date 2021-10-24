package com.ESSBG.app.Network;

import java.net.*;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;

/**
 * Author: Björn Rosengren
 *
 * Baseclass to reduce copy paste, easier testing.
 */
public abstract class SocketBaseListener implements Runnable {
    private final static ModelNetSerde serde = ModelNetSerde.getInstance();
    protected Socket socket;
    protected LinkedBlockingQueue<HashMapWithTypes> msgQueue;
    protected Lock lock;
    protected static volatile int numberOfActiveThreads = 0;

    protected SocketBaseListener(Socket socket, Lock lock, LinkedBlockingQueue<HashMapWithTypes> msgQueue) {
        this.socket = socket;
        this.lock = lock;
        this.msgQueue = msgQueue;
    }

    /**
     * Receives the entire message: 4-BYTE HEADER + MESSAGE
     *
     * @return JSONOBject
     */
    protected HashMapWithTypes recvAll() {
        try {
            InputStream stream = socket.getInputStream();

            // Get length of message
            byte[] headerLenByte = new byte[Constants.HEADERLEN];

            // Wait for getting contacted. Disconnect => return.
            if (stream.read(headerLenByte, 0, 1) == -1) {
                return null;
            }

            // If contact has been made, set timeout for the rest of msg.
            // For example bad actor, or very slow net.
            socket.setSoTimeout(500);
            stream.readNBytes(headerLenByte, 1, Constants.HEADERLEN - 1);

            // Get lengt of messages => Number of "chars".
            int msgLen = Converter.byteArrayToInt(headerLenByte);
            if (msgLen == 0) {
                return null;
            }
            // Read the message.
            String msg = new String(stream.readNBytes(msgLen));
            // IF message has been read correctly. Let socket have infinite timeout.
            socket.setSoTimeout(0);
            return serde.parse(msg);
        } catch (SocketException e) {
            // Don't care
        } catch (IOException e) {
            // Don't care, if server kicks the player etc...
            // Also how dare them to send me invalid data?!
        }
        return null;
    }

    // Disconnect itself, this causes an eruption of exceptions which we uses to
    // "stop" the server. A less crude method would be using variables to stop.
    protected void disconnectSocket() {
        try {
            socket.close();
        } catch (Exception ignore_because_user_is_not_welcome) {
        }
    }
}