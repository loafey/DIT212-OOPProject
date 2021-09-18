package com.ESSBG.app.Network;

import java.util.concurrent.*;
import org.json.*;

interface IClient {
    public boolean initClient();

    public void runClient();

    public void stopClient();

    public boolean sendData(JSONObject jsonobj);

    public LinkedBlockingQueue<JSONObject> getData();
}