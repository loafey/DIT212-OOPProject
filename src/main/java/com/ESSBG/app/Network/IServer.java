package com.ESSBG.app.Network;

import java.util.concurrent.*;
import org.json.*;

interface IServer {
    void runServer();

    boolean stopServer();

    boolean sendData(int player, JSONObject json);

    boolean initServer();

    ConcurrentLinkedQueue<JSONObject> getData();
}