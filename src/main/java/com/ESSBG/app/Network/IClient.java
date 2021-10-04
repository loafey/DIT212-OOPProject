package com.ESSBG.app.Network;

import java.io.IOException;
import java.util.concurrent.*;
import org.json.*;

/**
 * Order of Operation: <br>
 * 1: IClient c = new Client(); <br>
 * 2: c.runClient(); <br>
 *
 * After client has been run, you should be able to use sendData to send data.
 * getMsgData gives you a BLOCKING Fifo-queue to enable a waiting thread to
 * receive a message instead of busy-waiting/polling.
 */
interface IClient {
    /**
     * Starts the client and tries connects to server. Messages from the networking
     * module will be found in the msgQueue. <br>
     * Reason: net
     *
     * Resets the domain before each run.
     *
     * @return true if client started, else false. Connectionstatus in msgQueue.
     */
    boolean runClient();
    /**
     * Essentially the same as runClient() but with ip address as choice.
     *
     * @param ipAddress ip address in the shape of "123.123.123.123"
     * @return true if client started, else false. Connectionstatus in msgQueue.
     */
    boolean runClient(String ipAddress);

    /**
     * Closes the socket
     */
    void stopClient();

    /**
     * Sends the agreed structure between server and client of the jsonobject.
     *
     * @param jsonobj the agreed structure between server and client of the
     *                jsonobject.
     * @return IF FALSE, something went very wrong with networking. Probably loss of
     *         connection, rip.
     */
    boolean sendData(JSONObject jsonobj) throws IOException;

    /**
     * @return a BLOCKING Fifo-queue to enable a waiting thread to receive a message
     *         instead of busy-waiting/polling.<br>
     *         Datashape: <br>
     *         {"reason":"game", "data":{--Data_Parties_Agreed_On--}}<br>
     *         {"reason":"net", "data": bool} <br>
     *         => bool ? "Connected or Want to connect" : "Disconnected"
     */
    LinkedBlockingQueue<JSONObject> getMsgQueue();
}