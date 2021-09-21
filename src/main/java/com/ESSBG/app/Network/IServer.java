package com.ESSBG.app.Network;

import java.util.concurrent.*;
import java.util.*;
import org.json.*;
import java.io.*;

/**
 * Order of Operation:<br>
 * 1: IServer s = new Server(); <br>
 * 2: c.runServer(); <br>
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
     * Starts the server and spawns a helper class to serve the clients needs.
     * Messages from the networking module will be found in the msgQueue. <br>
     * Reason: net
     *
     * Resets the domain before each run.
     *
     * @throws IOException if Port is taken, change port.
     */
    void runServer() throws IOException;

    /**
     * Changes maxplayers to the current players and thus disallows more players to
     * join. Also shuts down the helper accepting class so no more players can join
     * unless game is over and runserver() is invoked.
     */
    void startGame();

    /**
     * Closes all clients and then closes server.
     */
    void stopServer();

    /**
     * Sends the agreed structure between server and client of the jsonobject.
     *
     * @param id   the receiver id to send the data.
     *
     * @param json the agreed structure between server and client of the jsonobject.
     * @return IF FALSE, something went very wrong with networking. Probably loss of
     *         connection, rip.
     * @throws IOException            if a socket accidentally disconnected. Threat
     *                                this as player disconnected, delete. Server
     *                                will know.
     * @throws NoSuchElementException Player disconnected from the server, you
     *                                should've known it by now. Fix!
     */
    boolean sendData(int id, JSONObject json) throws IOException, NoSuchElementException;

    /**
     * @return a BLOCKING Fifo-queue to enable a waiting thread to receive a message
     *         instead of busy-waiting/polling. <br>
     *         Datashape: <br>
     *         {"reason":"game", "id": int, "data":{--Data_Parties_Agreed_On--}}<br>
     *         {"reason":"net", "id": int, "data": bool} <br>
     *         => bool ? "Connected or Want to connect" : "Disconnected"
     */
    LinkedBlockingQueue<JSONObject> getMsgQueue();
}