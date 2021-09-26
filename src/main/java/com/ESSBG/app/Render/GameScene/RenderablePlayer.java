package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.Console;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class RenderablePlayer {
    private int coins;
    private int warPoints;

    public void updatePlayer(JSONObject data, Skin skin, Table handTable) {
        var handCardsJson = data.getJSONArray("handCards");
        var placedCardsJson = data.getJSONArray("placedCards");
        var resourcesJson = data.getJSONObject("resources");
        var monumentJson = data.getJSONObject("monument");
        var leftNeighbourJson = data.getJSONObject("leftNeighbour");
        var rightNeighbourJson = data.getJSONObject("rightNeighbour");

        for (var i : handCardsJson) {
            var k = (JSONObject) i;
            JSONObject colorString = k.getJSONObject("color");
            System.out.println(colorString);

            String cardText = "";
            try {
                var resource = k.getJSONObject("resource");
                cardText += resource.getString("type") + " " + resource.getInt("amount");
            } catch (JSONException e) {
            }
            try {
                var action = k.getJSONObject("action");
                cardText += "action";
            } catch (JSONException e) {
            }

            handTable.add(GenerateCard(skin, cardText, -5f)).width(84).height(128);
        }
    }

    private Button GenerateCard(Skin skin, String title, Float rotation) {
        Button card = new DrawableCard(skin, rotation);
        card.add(new Label(title, skin));
        return card;
    }
}
