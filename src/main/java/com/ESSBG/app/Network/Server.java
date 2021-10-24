package com.ESSBG.app.Network;

import org.json.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

/**
 * @author: Björn Rosengren
 *
 * The "main" part of the server.
 *
 * Handles the connected users and max connected users. In our case, since we
 * are making a game, variables are more fitting to the context rather than
 * being a very generic server. A generic server would also be much less
 * abstract, but harder to use.
 */
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
        try {
            super.sendData(clientSocket, jsonobj);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

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

    @Override
    public boolean isSocketClosed() {
        return socket.isClosed();
    }

    /**
     * The reason for these methods are because that the server is "too" simple in a
     * regard. These should be publicly accessed in a more advanced server where the
     * operators/owners needs a higher degree of control. But since the project was
     * to keep it simple for everyone to be able to use the class, then corners had
     * to be cut.
     */

    // Only for testing
    protected boolean hasUserJoined(int id) {
        return hashMap.containsKey(id);
    }

    // Only for testing or not. Disconnects the user. For an eventual kick method?
    protected void disconnectUserSocket(int id) throws IOException {
        hashMap.get(id).close();
    }

    // Only for testing
    protected int getNumberOfUsers() {
        return hashMap.size();
    }
}