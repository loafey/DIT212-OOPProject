package com.ESSBG.app.Network;

import java.util.concurrent.*;
import org.json.*;
import java.io.*;

/**
 * Order of Operation:<br>
 * 1: IServer s = new Server();<br>
 * 2: s.initServer();<br>
 * 3: c.runServer();<br>
 *
 * After server has been run, you should be able to use sendData to send data to
 * id. getMsgData gives you a BLOCKING Fifo-queue to enable a waiting thread to
 * receive a message instead of busy-waiting/polling.
 *
 * To restart a game with new network: <br>
 * s.stopServer() <br>
 * s = new Server();<br>
 * And then execute order of operation.
 *
 */
interface IServer {
    /**
     * Initializes the server.
     *
     * @return true if initialization was successful, otherwise false. Try again.
     */
    public boolean initServer();

    /**
     * Starts the server and spawns a helper class to serve the clients needs.
     * Messages from the networking module will be found in the msgQueue. <br>
     * Reason: net
     */
    public void runServer();

    /**
     * Changes maxplayers to the current players and thus disallows more players to
     * join.
     */
    public void startGame();

    /**
     * Closes all clients and then closes server.
     */
    public void stopServer();

    /**
     * Sends the agreed structure between server and client of the jsonobject.
     * STRONGLY RECOMMENDED TO USE JSONFactory.getGame(<Your data here>) as your
     * JSONObject to send to the receiver.
     *
     * @param id      the receiver id to send the data.
     *
     * @param jsonobj the agreed structure between server and client of the
     *                jsonobject.
     * @return IF FALSE, something went very wrong with networking. Probably loss of
     *         connection, rip.
     * @throws UnsupportedEncodingException
     */
    public boolean sendData(int id, JSONObject json) throws UnsupportedEncodingException, Exception;

    /**
     * @return a BLOCKING Fifo-queue to enable a waiting thread to receive a message
     *         instead of busy-waiting/polling.
     */
    public LinkedBlockingQueue<JSONObject> getMsgQueue();
}