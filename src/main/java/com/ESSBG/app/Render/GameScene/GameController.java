package com.ESSBG.app.Render.GameScene;

import com.ESSBG.app.Network.Client;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.json.JSONObject;

public class GameController {
    private Client client;

    public GameController (Client client) {
        this.client = client;
    }

    public void assignCardButton(Button card,int cardIndex) {
        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cardAction(cardIndex);
            }
        });
    }

    /**
     * 
     * @param cardIndex
     */
    private void cardAction(int cardIndex){
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
