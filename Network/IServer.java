package com.ESSBG.app.Network;

import java.util.concurrent.*;
import org.json.*;

interface IServer {
    public void runServer();

    public boolean initServer();

    public boolean stopServer();

    public boolean sendData(int player, JSONObject json);

    public ConcurrentLinkedQueue<JSONObject> getData();
}