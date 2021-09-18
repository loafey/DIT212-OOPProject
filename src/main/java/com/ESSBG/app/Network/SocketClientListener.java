package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

import org.json.JSONObject;

public class SocketClientListener extends SocketBaseListener {
    public SocketClientListener(Socket socket, Lock lock, LinkedBlockingQueue<JSONObject> msgQueue) {
        super(socket, lock, msgQueue);
    }

    @Override
    public void run() {
        try {
            // Write initial message to server.
            if (!writeToSocket(JSONFactory.getNetwork(true).toString())) {
                // Tell client that connection failed.
                msgQueue.add(JSONFactory.getNetwork(false));
                disconnectSocket();
                return;
            }
            while (receiveDataPushToQueue()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean receiveDataPushToQueue() {
        JSONObject recvData = recvAll();
        // Disconnect or any networking error.
        if (recvData == null) {
            // Tell listener that a socket has been disconnected
            // and that we are dying. Send a last message.
            msgQueue.add(JSONFactory.getNetwork(false));
            disconnectSocket();
            return false;
        }
        msgQueue.add(JSONFactory.getGame(recvData));
        return true;
    }

    private boolean writeToSocket(String s) {
        try {
            OutputStream stream = socket.getOutputStream();
            // Send length of message
            stream.write(Converter.intToByteArray(s.length()));
            // Send rest of the message. Just like painting an owl...
            stream.write(s.getBytes());
            // Assume everything went well.
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
