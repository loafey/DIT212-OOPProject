package com.ESSBG.app.Render.GameScene;

import java.io.IOException;

import com.ESSBG.app.Network.IClient;
import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.GameScene.Elements.DrawableBoard;
import com.ESSBG.app.Render.StartMenu.StartMenu;
import com.ESSBG.app.Render.StartMenu.StartMenuController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Author: Samuel Hammersberg
 */
public class GameController {
    private IClient client;
    private Skin skin;
    private DrawableBoard board;
    private Table sceneTable;

    public GameController(IClient client, Skin skin, DrawableBoard board, Table sceneTable) {
        this.client = client;
        this.skin = skin;
        this.board = board;
        this.sceneTable = sceneTable;
    }

    /**
     * Assigns the click action of the passed in button to cardAction (See
     * GameController.java).
     * 
     * @param card
     * @param cardIndex
     */
    public void assignCardButton(Button card, int cardIndex) {
        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cardAction(cardIndex, card);
            }
        });
    }

    /**
     * When a card is pressed, clear the content of the card, and instead display
     * three buttons, a place button, a discard button, and a monument upgrade
     * button.
     * 
     * @param cardIndex
     */
    private void cardAction(int cardIndex, Button card) {
        // remove the preexisting click action
        // otherwise you can theoretically create an infinite amount of these buttons.
        card.clearListeners();
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

    // Creates a JSON message and sends it to the server through the client.
    private void action(int cardIndex, String actionType) {
        JSONObject actionData = new JSONObject();
        actionData.put("msgNum", 0);

        JSONObject data = new JSONObject();
        data.put("cardIndex", cardIndex);
        data.put("action", actionType);
        actionData.put("card", data);
        try {
            client.sendData(actionData);
        } catch (IOException e) {
        }
    }

    public void displayScores(JSONObject data) {
        JSONArray scoreList = data.getJSONArray("scores");
        sceneTable.clear();

        String scoreText = "Name: \t| Score:\n";
        for (Object so : scoreList) {
            JSONObject sdata = (JSONObject) so;
            scoreText += sdata.getString("name") + "\t" + sdata.getInt("score") + "\n";
        }

        sceneTable.center();
        sceneTable.add(new Label(scoreText, skin));
        sceneTable.row();

        Button backButton = new Button(skin);
        backButton.add(new Label("Back", skin));
        sceneTable.add(backButton);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                StartMenu sm = new StartMenu();
                new StartMenuController(sm);
                ScreenManager.getInstance().setScreen(sm);
            }
        });
    }

    // should preferably use observer pattern instead.
    public void pollClient() {
        if (client != null) {
            if (client.getMsgQueue().size() > 0) {
                try {
                    JSONObject msg = client.getMsgQueue().take();
                    if (msg.getString("reason").equals("game")){
                        JSONObject data = msg.getJSONObject("data");
                        if (data.has("reply") && data.getBoolean("reply")) {
                            board.hideHandCards();
                        }
                        if (data.has("placedCards")) {
                            board.updateBoard(data);
                            int i = 0;
                            for (Button card : board.getCards()) {
                                assignCardButton(card, i);
                                i++;
                            }
                        }
                        if (data.has("scores")) {
                            displayScores(data);
                        }
                    } else if (msg.getString("reason").equals("net")){
                        if (!msg.getBoolean("data")) {
                            client.stopClient();
                        }
                    }

                } catch (InterruptedException e) {}
            }
        }
    }
}
