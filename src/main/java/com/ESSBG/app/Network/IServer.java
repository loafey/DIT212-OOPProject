package com.ESSBG.app.Network;

import java.util.concurrent.*;
import org.json.*;

interface IServer {
    public void runServer();

    public boolean initServer();

    public void startGame();

    public void stopServer();

    public boolean sendData(int player, JSONObject json) throws Exception;

    public LinkedBlockingQueue<JSONObject> getMsgQueue();
}