package com.ESSBG.app.Network;

import org.junit.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import static org.junit.Assert.*;

public class NetworkTest {
    // Since we need to start thread(s) and then message eachother, we need to have
    // a small delay. 10ms was the lowest I(bjorn) could reach.
    final int SLEEP_TIME = 500;
    IServer s;
    IClient c;
    LinkedBlockingQueue<HashMapWithTypes> serverMsgQueue;
    LinkedBlockingQueue<HashMapWithTypes> clientMsgQueue;
    ModelNetSerde serde = ModelNetSerde.getInstance();

    @Before
    public void setup() throws InterruptedException, IOException {
        s = new Server();
        s.runServer();
        serverMsgQueue = s.getMsgQueue();
        c = new Client();
        c.runClient();
        clientMsgQueue = c.getMsgQueue();
        clientMsgQueue.take();
    }

    @After
    public void shutdown() throws InterruptedException {
        s.stopServer();
        c.stopClient();
        Thread.sleep(500);
    }

    @Test
    public void testConverterZero() {
        int v = 0;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(4, v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterMax() {
        int v = Integer.MAX_VALUE;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(4, v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterHalfMax() {
        int v = Integer.MAX_VALUE / 2;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(4, v));
        assertTrue(v == n);
    }

    @Test
    public void testConverterTen() {
        int v = 10;
        int n = Converter.byteArrayToInt(Converter.intToByteArray(4, v));
        assertTrue(v == n);
    }

    @Test
    public void clientConnect() throws Exception {
        boolean b = false;
        // Give it some chance for message to arrive, minimum value 10ms me(bjorn).
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() >= 1) {
            HashMapWithTypes js = serverMsgQueue.take();
            int i = js.getInt("id");
            b = ((Server) s).hasUserJoined(i);
            assertTrue(b);
        } else {
            fail();
        }
    }

    @Test
    public void clientConnectMaxDisconnectAll() throws IOException, InterruptedException {
        // Spawn extra n-1 for
        for (int i = 0; i < Constants.MAXPLAYERS; i++) {
            Client c = new Client();
            c.runClient();
        }

        // Give it some chance for message to arrive, minimum value 10ms me(bjorn).
        Thread.sleep(SLEEP_TIME);
        Integer[] idList = new Integer[Constants.MAXPLAYERS];
        if (serverMsgQueue.size() == Constants.MAXPLAYERS) {
            Server x = (Server) s;
            for (int i = 0; i < Constants.MAXPLAYERS; i++) {
                idList[i] = serverMsgQueue.take().getInt("id");
                assertTrue(((Server) s).hasUserJoined(idList[i]));
            }
            Set<Integer> p = new HashSet<Integer>(Arrays.asList(idList));
            assertTrue(p.size() == Constants.MAXPLAYERS);
            assertTrue(x.getNumberOfUsers() == Constants.MAXPLAYERS);
            for (int i = 0; i < Constants.MAXPLAYERS; i++) {
                x.disconnectUserSocket(idList[i]);
            }
            Thread.sleep(SLEEP_TIME);
            assertTrue(x.getNumberOfUsers() == 0);
            assertTrue(serverMsgQueue.size() == Constants.MAXPLAYERS);
            for (int i = 0; i < Constants.MAXPLAYERS; i++) {
                assertTrue(p.contains(serverMsgQueue.take().getInt("id")));
                assertFalse(x.hasUserJoined(idList[i]));
            }
            return;
        }
        fail();
    }

    @Test
    public void connectAllButOneStartGameAndAddLast() throws InterruptedException {
        Server x = (Server) s;
        for (int i = 0; i < Constants.MAXPLAYERS - 2; i++) {
            Client c = new Client();
            c.runClient();
        }
        Thread.sleep(SLEEP_TIME);
        int y = x.getNumberOfUsers();
        x.startGame();
        Thread.sleep(SLEEP_TIME);
        Client c = new Client();
        assertFalse(c.runClient());
        c.runClient();
        Thread.sleep(SLEEP_TIME);
        assertTrue(y == Constants.MAXPLAYERS - 1 && y == x.getNumberOfUsers());
    }

    @Test
    public void twoConnectSendMessToOne() throws Exception {
        Server server = (Server) s;
        Thread.sleep(200);
        Client d = new Client();
        d.runClient();
        Thread.sleep(SLEEP_TIME);

        int s = clientMsgQueue.size();
        int c_id = serverMsgQueue.take().getInt("id");
        int b_id = serverMsgQueue.take().getInt("id");
        server.sendData(c_id, new HashMapWithTypes());
        Thread.sleep(SLEEP_TIME);

        assertTrue(s + 1 == clientMsgQueue.size());
        assertTrue(d.getMsgQueue().size() == 1);
        server.sendData(b_id, new HashMapWithTypes());
        Thread.sleep(SLEEP_TIME);
        assertTrue(d.getMsgQueue().size() == 2);
        assertTrue(s + 1 == clientMsgQueue.size());
    }

    @Test
    public void serverTestSendMessage() throws Exception {
        int id = 0;
        // Give it some chance for message to arrive, minimum value 10ms me(bjorn).
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() >= 1) {
            HashMapWithTypes d = serverMsgQueue.take();
            id = d.getInt("id");
            s.sendData(id, new HashMapWithTypes());
            // Give it some time for msg to arrive to client.
            Thread.sleep(SLEEP_TIME);
            if (clientMsgQueue.size() >= 1) {
                HashMapWithTypes o = clientMsgQueue.take();
                try {
                    assertTrue(o.getHashMapWithTypes("data") instanceof HashMapWithTypes);
                    return;
                } catch (Exception e) {
                }
            }
        }
        fail();
    }

    @Test
    public void clientTestSendMessage() throws UnsupportedEncodingException, Exception {
        int id = 0;
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() >= 1) {
            HashMapWithTypes d = serverMsgQueue.take();
            id = d.getInt("id");
            // Send data to server
            c.sendData(ReplyFactory.getGame(new HashMapWithTypes()));
            Thread.sleep(SLEEP_TIME);
            if (serverMsgQueue.size() >= 1) {
                HashMapWithTypes o = serverMsgQueue.take();
                try {
                    assertTrue(id == o.getInt("id"));
                    assertTrue(o.getHashMapWithTypes("data") instanceof HashMapWithTypes);
                    return;
                } catch (Exception e) {
                }
            }
        }
        fail();
    }

    @Test
    public void clientSendFiveMessages() throws UnsupportedEncodingException, Exception {
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() >= 1) {
            serverMsgQueue.take();
            // Send data to server
            for (int i = 0; i < 5; i++) {
                c.sendData(new HashMapWithTypes("{\"" + String.valueOf(i) + "\":" + String.valueOf(i) + "}"));
            }
            Thread.sleep(SLEEP_TIME);
            if (serverMsgQueue.size() >= 1) {
                try {
                    for (int i = 0; i < 5; i++) {
                        HashMapWithTypes o = serverMsgQueue.take();
                        assertTrue(o.getHashMapWithTypes("data").getInt(String.valueOf(i)) == i);
                    }
                    return;
                } catch (Exception e) {
                }
            }
        }
        fail();
    }

    @Test
    public void serverSendFiveMessages() throws UnsupportedEncodingException, Exception {
        int id = 0;
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() >= 1) {
            HashMapWithTypes d = serverMsgQueue.take();
            id = d.getInt("id");
            for (int i = 0; i < 5; i++) {
                s.sendData(id, new HashMapWithTypes("{\"" + String.valueOf(i) + "\":" + String.valueOf(i) + "}"));
            }
            if (clientMsgQueue.size() >= 1) {
                try {
                    for (int i = 0; i < 5; i++) {
                        HashMapWithTypes o = clientMsgQueue.take();
                        assertTrue(o.getHashMapWithTypes("data").getInt(String.valueOf(i)) == i);
                    }
                    return;
                } catch (Exception e) {
                }
            }
        }
        fail();
    }

    @Test
    public void sendEachOther() throws UnsupportedEncodingException, Exception {
        int id = 0;
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() == 0) {
            fail();
        }
        HashMapWithTypes d = serverMsgQueue.take();
        id = d.getInt("id");

        s.sendData(id, new HashMapWithTypes());
        c.sendData(new HashMapWithTypes());
        c.sendData(new HashMapWithTypes());
        s.sendData(id, new HashMapWithTypes());
        Thread.sleep(SLEEP_TIME);

        assertTrue(serverMsgQueue.size() == 2);
        assertTrue(clientMsgQueue.size() == 2);
    }

    @Test
    public void clientDisconnects() throws UnsupportedEncodingException, Exception {
        Thread.sleep(SLEEP_TIME);
        if (serverMsgQueue.size() == 0) {
            fail();
        }
        int i = serverMsgQueue.take().getInt("id"); // Hello msg with id etc..
        assertTrue(((Server) s).hasUserJoined(i));
        c.stopClient();
        Thread.sleep(SLEEP_TIME);
        assertFalse(((Client) c).isListenerRunning());
        assertFalse(clientMsgQueue.take().getBoolean("data"));

        HashMapWithTypes o = serverMsgQueue.take();
        assertFalse(o.getBoolean("data"));
        assertTrue(o.getInt("id") == i);
        assertFalse(((Server) s).hasUserJoined(i));
    }
}