package com.ESSBG.app.Render.GameScene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// TODO REMOVE ALL MAGIC NUMBERS

public class DrawableBoard {
    private int coins;
    private int warPoints;

    public void updateBoard(JSONObject data, Skin skin, Table handTable, Table placedCards) {
        updateHand(skin, handTable, data.getJSONArray("handCards"));
        updatePlacedCards(skin, placedCards, data.getJSONArray("placedCards"));
        var resourcesJson = data.getJSONObject("resources");
        var monumentJson = data.getJSONObject("monument");
        var leftNeighbourJson = data.getJSONObject("leftNeighbour");
        var rightNeighbourJson = data.getJSONObject("rightNeighbour");
    }

    private void updatePlacedCards(Skin skin, Table placedCardsTable, JSONArray cards) {
        placedCardsTable.clear();
        HashMap<Color, ArrayList<Button>> sortedCards = new HashMap<>();
        cards.forEach(cardData -> {
            Button card = GenerateCard(skin, (JSONObject) cardData, 0f);
            Color color = card.getColor();

            if (sortedCards.containsKey(color)) {
                sortedCards.get(color).add(card);
            } else {
                ArrayList<Button> list = new ArrayList<>();
                list.add(card);
                sortedCards.put(color, list);
            }
        });

        sortedCards.forEach((color, list) -> {
            Table newTable = new Table();
            list.forEach(card -> {
                newTable.add(card);
                newTable.row();
            });
            placedCardsTable.add(newTable);
        });
    }

    private void updateHand(Skin skin, Table handTable, JSONArray handCards) {
        handTable.clear();
        handCards.forEach(card -> {
            handTable.add(GenerateCard(skin, (JSONObject) card, -5f)).width(84).height(128);
        });
    }

    private Button GenerateCard(Skin skin, JSONObject cardData, Float rotation) {
        JSONObject colorString = cardData.getJSONObject("color");
        Color color = new Color(
            colorString.getFloat("r"),
            colorString.getFloat("g"),
            colorString.getFloat("b"),
            colorString.getFloat("a")
        );

        String cardText = "";
        try {
            var resource = cardData.getJSONObject("resource");
            cardText += resource.getString("type") + " " + resource.getInt("amount");
        } catch (JSONException e) {
        }
        try {
            var action = cardData.getJSONObject("action");
            cardText += "action";
        } catch (JSONException e) {
        }

        Button card = new DrawableCard(skin, rotation, color);
        card.add(new Label(cardText, skin));
        return card;
    }
}
