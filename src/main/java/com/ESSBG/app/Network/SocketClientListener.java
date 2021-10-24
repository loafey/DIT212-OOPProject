package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * Author: Björn Rosengren
 *
 * This class is only for detaching itself from the main-thread to let the
 * client receive messages from the server. It might work without threading, but
 * then there is the risk of 100% cpu or the entire program deadlocks.
 */
public class SocketClientListener extends SocketBaseListener {
    private static final ModelNetSerde serde = ModelNetSerde.getInstance();

    protected SocketClientListener(Socket socket, Lock lock, LinkedBlockingQueue<HashMapWithTypes> msgQueue) {
        super(socket, lock, msgQueue);
    }

    @Override
    public void run() {
        try {
            // Write initial message to server.
            if (!connectHandshake(serde.serialize(ReplyFactory.getNetwork(true)))) {
                // Tell client that connection failed.
                msgQueue.add(ReplyFactory.getNetwork(false));
                disconnectSocket();
                return;
            }
            // Tell listener that connection was successful.
            msgQueue.add(ReplyFactory.getNetwork(true));
            while (receiveDataPushToQueue()) {
            }
        } catch (Exception ignore_since_any_inception_here_is_a_disconnect) {
        }
    }

    private boolean receiveDataPushToQueue() {
        HashMapWithTypes recvData = recvAll();
        // Disconnect or any networking error.
        if (recvData == null) {
            // Tell listener that a socket has been disconnected
            // and that we are dying. Send a last message.
            msgQueue.add(ReplyFactory.getNetwork(false));
            disconnectSocket();
            return false;
        }
        msgQueue.add(ReplyFactory.getGame(recvData));
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