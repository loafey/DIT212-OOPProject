package com.ESSBG.app.Network;

import java.util.concurrent.*;
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
public interface IServer {
    /**
     * Starts the server and spawns a helper class to serve the clients needs.
     * Messages from the networking module will be found in the msgQueue. <br>
     * Reason: net
     *
     * Resets the domain before each run.
     *
     * @return true if server started, else false
     */
    public boolean runServer();

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
     *         instead of busy-waiting/polling. <br>
     *         Datashape: <br>
     *         {"reason":"game", "id": int, "data":{--Data_Parties_Agreed_On--}}<br>
     *         {"reason":"net", "id": int, "data": bool} <br>
     *         => bool ? "Connected or Want to connect" : "Disconnected"
     */
    public LinkedBlockingQueue<JSONObject> getMsgQueue();
}