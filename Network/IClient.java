package com.ESSBG.app.Network;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.*;

interface IClient {
    public boolean initClient();

    public void runClient();

    public boolean stopClient();

    public boolean sendData(JSONObject jsonobj);

    public ConcurrentLinkedQueue<JSONObject> getData();
}