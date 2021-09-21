package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

public class Server extends Base implements IServer {
    private ConcurrentHashMap<Integer, Socket> hashMap;
    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ServerSocket socket;
    // This is to use first index as a remote controller of socketlistener.
    private volatile int[] maxplayersAtIndexZero = { Constants.MAXPLAYERS };

    @Override
    public void runServer() throws IOException {
        // Shut down existing sockets
        try {
            socket.close();
        } catch (Exception ignore_since_we_forcefully_close_it) {
        }
        // Reset "world"
        maxplayersAtIndexZero[0] = Constants.MAXPLAYERS;
        msgQueue = new LinkedBlockingQueue<JSONObject>();
        hashMap = new ConcurrentHashMap<Integer, Socket>();
        socket = new ServerSocket(Constants.PORT);
        (new Thread(new SocketServer(socket, hashMap, msgQueue, maxplayersAtIndexZero))).start();
    }

    @Override
    public boolean sendData(int id, JSONObject jsonobj) throws IOException, NoSuchElementException {
        if (socket == null || socket.isClosed()) {
            return false;
        }
        Socket clientSocket = hashMap.get(id);
        // Check whether player values are correct.
        if (clientSocket == null) {
            throw new NoSuchElementException("> User is disconnected.");
        }
        super.sendData(clientSocket, jsonobj);
        return true;
    }

    @Override
    public void startGame() {
        maxplayersAtIndexZero[0] = hashMap.size();
        try {
            socket.close();
        } catch (Exception ignore_since_we_forcefully_close_it) {
        }
    }

    @Override
    public LinkedBlockingQueue<JSONObject> getMsgQueue() {
        return this.msgQueue;
    }

    @Override
    public void stopServer() {
        hashMap.values().forEach(socket -> {
            try {
                socket.close();
            } catch (Exception e) {
            }
        });
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    // Only for testing
    protected boolean hasUserJoined(int id) {
        return hashMap.containsKey(id);
    }

    // Only for testing or not. Disconnects the user.
    protected void disconnectUserSocket(int id) throws IOException {
        hashMap.get(id).close();
    }

    protected int getNumberOfUsers() {
        return hashMap.size();
    }
}