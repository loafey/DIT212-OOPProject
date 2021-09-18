package com.ESSBG.app.Network;

import com.ESSBG.app.Network.*;
import org.junit.Before;
import org.hamcrest.core.IsInstanceOf;
import org.json.*;
import org.junit.After;
import org.junit.Test;

import java.util.*;
import java.io.*;
import java.net.*;
import static java.lang.System.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import static org.junit.Assert.*;

public class NetworkTest {
    IServer s;
    IClient c;
    LinkedBlockingQueue<JSONObject> serverMsgQueue;
    LinkedBlockingQueue<JSONObject> clientMsgQueue;

    @Before
    public void setup() throws InterruptedException {
        s = new Server();
        s.initServer();
        s.runServer();
        serverMsgQueue = s.getMsgQueue();
        c = new Client();
        c.initClient();
        c.runClient();
        clientMsgQueue = c.getMsgQueue();
    }

    @After
    public void shutdown() {
        s.stopServer();
        c.stopClient();
    }

    @Test
    public void testConverterZero() {
        int v = 0;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterMax() {
        int v = Integer.MAX_VALUE;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterHalfMax() {
        int v = Integer.MAX_VALUE / 2;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterTen() {
        int v = 10;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(v));
        assertTrue(v == n);
    }

    @Test
    public void clientConnect() throws Exception {
        boolean b = false;
        // Give it some chance for message to arrive, minimum value 10ms me(bjorn).
        Thread.sleep(100);
        if (serverMsgQueue.size() >= 1) {
            JSONObject js = serverMsgQueue.take();
            int i = js.getInt("id");
            b = ((Server) s).hasUserJoined(i);
        } else {
            fail();
        }
        assertTrue(b);
    }

    @Test
    public void serverTestSendMessage() throws UnsupportedEncodingException, Exception {
        int id = 0;
        // Give it some chance for message to arrive, minimum value 10ms me(bjorn).
        Thread.sleep(100);
        if (serverMsgQueue.size() >= 1) {
            JSONObject js = serverMsgQueue.take();
            id = js.getInt("id");
            s.sendData(id, new JSONObject());
            // Give it some time for msg to arrive to client.
            Thread.sleep(100);
            if (clientMsgQueue.size() >= 1) {
                JSONObject o = clientMsgQueue.take();
                try {
                    assertTrue(o.getJSONObject("data") instanceof JSONObject);
                    return;
                } catch (Exception e) {
                }
            }
        }
        fail();
    }
}
