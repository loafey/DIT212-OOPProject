package com.ESSBG.app.Render.GameScene;

import com.ESSBG.app.Network.Client;

import org.json.JSONObject;

public class GameController {
    private Client client;

    public GameController (Client client) {
        this.client = client;
    }

    /**
     * 
     * @param cardIndex
     */
    public void cardAction(int cardIndex){
        JSONObject actionData = new JSONObject();
        actionData.put("msgNum", 0);

        JSONObject card = new JSONObject();
        card.put("cardIndex",cardIndex);
        card.put("action", "place");


        actionData.put("card", card);
        System.out.println(actionData);
        client.sendData(actionData);
    }
}
