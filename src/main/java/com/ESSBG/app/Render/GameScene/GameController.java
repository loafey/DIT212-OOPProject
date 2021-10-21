package com.ESSBG.app.Render.GameScene;

import com.ESSBG.app.Network.Client;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.json.JSONObject;

public class GameController {
    private Client client;
    private Skin skin;

    public GameController (Client client, Skin skin) {
        this.client = client;
        this.skin = skin;
    }

    public void assignCardButton(Button card,int cardIndex) {
        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cardAction(cardIndex, card);
            }
        });
    }

    /**
     * 
     * @param cardIndex
     */
    private void cardAction(int cardIndex, Button card){
        Button placeButton = new Button(skin);
        Label placeText = new Label("Place", skin);
        placeText.setFontScale(0.5f);
        placeButton.add(placeText);
        placeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action(cardIndex, "place");
            }
        });

        Button discardButton = new Button(skin);
        Label discardText = new Label("Discard", skin);
        discardText.setFontScale(0.5f);
        discardButton.add(discardText);
        discardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action(cardIndex, "discard");
            }
        });

        Button monumentButton = new Button(skin);
        Label monumentText = new Label("Monument", skin);
        monumentText.setFontScale(0.5f);
        monumentButton.add(monumentText);
        monumentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action(cardIndex, "monument");
            }
        });

        card.clear();
        card.add(placeButton);
        card.row();
        card.add(discardButton);
        card.row();
        card.add(monumentButton);
    }

    private void action(int cardIndex, String actionType){
        JSONObject actionData = new JSONObject();
        actionData.put("msgNum", 0);

        JSONObject data = new JSONObject();
        data.put("cardIndex",cardIndex);
        data.put("action", actionType);
        actionData.put("card", data);
        client.sendData(actionData);
    }
}
