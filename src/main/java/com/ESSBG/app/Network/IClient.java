package com.ESSBG.app.Network;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;
import org.json.*;

/**
 * Order of Operation:<br>
 * 1: IClient c = new Client();<br>
 * 2: c.initClient();<br>
 * 3: c.runClient();<br>
 *
 * After client has been run, you should be able to use sendData to send data.
 * getMsgData gives you a BLOCKING Fifo-queue to enable a waiting thread to
 * receive a message instead of busy-waiting/polling.
 */
public interface IClient {
    /**
     * Initializes the client.
     *
     * @return true if initialization was successful, otherwise false.
     */
    public boolean initClient();

    /**
     * Starts the client and tries connects to server. Messages from the networking
     * module will be found in the msgQueue. <br>
     * Reason: net
     */
    public void runClient();

    /**
     * Closes the socket
     */
    public void stopClient();

    /**
     * Sends the agreed structure between server and client of the jsonobject.
     * STRONGLY RECOMMENDED TO USE JSONFactory.getGame(<Your data here>) as your
     * JSONObject to send to the receiver.
     *
     * @param jsonobj the agreed structure between server and client of the
     *                jsonobject.
     * @return IF FALSE, something went very wrong with networking. Probably loss of
     *         connection, rip.
     * @throws UnsupportedEncodingException
     */
    public boolean sendData(JSONObject jsonobj) throws UnsupportedEncodingException;

    /**
     * @return a BLOCKING Fifo-queue to enable a waiting thread to receive a message
     *         instead of busy-waiting/polling.
     */
    public LinkedBlockingQueue<JSONObject> getMsgQueue();
}