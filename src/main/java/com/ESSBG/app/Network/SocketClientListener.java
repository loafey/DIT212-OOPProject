package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;

public class SocketClientListener extends SocketBaseListener {
    protected SocketClientListener(Socket socket, Lock lock, LinkedBlockingQueue<JSONObject> msgQueue) {
        super(socket, lock, msgQueue);
    }

    @Override
    public void run() {
        try {
            // Write initial message to server.
            if (!connectHandshake(JSONFactory.getNetwork(true).toString())) {
                // Tell client that connection failed.
                msgQueue.add(JSONFactory.getNetwork(false));
                disconnectSocket();
                return;
            }
            // Tell listener that connection was successful.
            msgQueue.add(JSONFactory.getNetwork(true));
            while (receiveDataPushToQueue()) {
            }
        } catch (Exception ignore_since_any_inception_here_is_a_disconnect) {
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

    private boolean connectHandshake(String s) {
        try {
            OutputStream stream = socket.getOutputStream();
            // Send length of message
            stream.write(Converter.intToByteArray(4, s.length()));
            // Send rest of the message. Just like painting an owl...
            stream.write(s.getBytes());
            // Assume everything went well.
            return true;
        } catch (IllegalArgumentException e) {
            // IF we accidentally sent a bad message
            e.printStackTrace();
        } catch (Exception dont_care) {
        }
        return false;
    }
}