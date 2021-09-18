package com.ESSBG.app.Model;

import com.ESSBG.app.Network.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class NetworkTest {
    IServer s;
    IClient c;

    @Before
    public void setup() throws InterruptedException {
        s = new Server();
        s.initServer();
        s.runServer();
        Thread.sleep(200);
        c = new Client();
        c.initClient();
        c.runClient();
    }

    @After
    public void shutdown() {
        s.stopServer();
        c.stopClient();
    }

    @Test
    public void testSendMessage() {
        return;
    }
}
