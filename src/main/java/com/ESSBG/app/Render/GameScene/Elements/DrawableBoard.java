package com.ESSBG.app.Render.GameScene.Elements;

import com.ESSBG.app.Render.GameScene.GameController;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DrawableBoard {
    /**
     * Updates the board 
     * @param data The data to be displayed
     * @param controller A GameController so the buttons can be linked up to appropriate actions when clicked.
     * @param skin The skin to be used
     * @param handTable The table where cards in your hand should be displayed
     * @param placedCards The table where cards you have placed should be displayed
     * @param monument The table for the monument
     */
    public void updateBoard(JSONObject data, GameController controller, Skin skin, Table handTable, Table placedCards, Table monument) {
        updateHand(skin, controller, handTable, data.getJSONArray("handCards"));
        updatePlacedCards(skin, placedCards, data.getJSONArray("placedCards"));
        updateResources(skin, monument, data.getJSONObject("resources"));
        updateMonument(skin, monument, data.getJSONObject("monument"));
        var leftNeighbourJson = data.getJSONObject("leftNeighbour");
        var rightNeighbourJson = data.getJSONObject("rightNeighbour");
    }

    /**
     * Clears and redraws the monument with the new data
     * @param skin The skin to be used
     * @param monument The table for the monument
     * @param data The data to be displayed
     */
    private void updateMonument(Skin skin, Table monument, JSONObject data) {
        monument.clear();
        monument.row();
        
        Table cardTable = new Table();

        int unlocked = data.getInt("unlocked");
        
        int count = 0;
        for (var i : data.getJSONArray("cards")){
            JSONObject card = (JSONObject)i;
            String type = card.getString("type");
            int amount = card.getInt("amount");

            Button b = new Button(skin);
            b.add(new Label(type + ": " + amount,skin));
            if (count < unlocked) {
                b.setColor(new Color(0, 1, 0, 1));
            }
            cardTable.add(b);

            count++;
        }
        monument.add(cardTable);
    }

    /**
     * Displays coins and war points in the monument.
     * @param skin The skin to be used
     * @param monument The table for the monument
     * @param data The data to be displayed
     */
    private void updateResources(Skin skin, Table monument, JSONObject data) {
        monument.row();
        monument.add(new Label("Coins: " + data.getInt("coins"), skin));
        monument.row();
        monument.add(new Label("War: " + data.getInt("war"), skin));
    }

    /**
     * Displays the cards you have placed.
     * @param skin The skin to be used
     * @param monument The table for the monument
     * @param cards The cards to be displayed
     */
    private void updatePlacedCards(Skin skin, Table placedCardsTable, JSONArray cards) {
        placedCardsTable.clear();
        // Sorts the cards into their respective colours by 
        // placing them in the ArrayList that corresponds to their color.
        HashMap<Color, ArrayList<Button>> sortedCards = new HashMap<>();
        cards.forEach(cardData -> {
            Button card = GenerateCard(skin, (JSONObject) cardData, 0f);
            Color color = card.getColor();

            if (!sortedCards.containsKey(color)) {
                ArrayList<Button> list = new ArrayList<>();
                sortedCards.put(color, list);
            }
            sortedCards.get(color).add(card);
        });

        // Iterate over all the ArrayLists, creating a new table for each one of them.
        sortedCards.forEach((color, list) -> {
            Table newTable = new Table();
            list.forEach(card -> {
                newTable.add(card);
                newTable.row();
            });
            placedCardsTable.add(newTable);
        });
    }

    /**
     * Updates the cards displayed in your hand
     * @param skin The skin to be used
     * @param gameController A GameController so the buttons can be linked up to appropriate actions when clicked.
     * @param handTable The table to contain the cards
     * @param handCards The card data to be displayed
     */
    private void updateHand(Skin skin, GameController gameController, Table handTable, JSONArray handCards) {
        handTable.clear();
        int index = 0;
        for (Object cardData : handCards){
            Button card = GenerateCard(skin, (JSONObject) cardData, -5f);
            handTable.add(card).width(84).height(128);

            // The index must be copied so it exists in the same scope as the new object.
            int clone = index;
            card.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameController.cardAction(clone);
                }
            });
            index++;
        }
    }

    /**
     * Generates a card
     * @param skin The skin to be used
     * @param cardData The cards data
     * @param rotation The rotation of the card
     * @return
     */
    private Button GenerateCard(Skin skin, JSONObject cardData, Float rotation) {
        JSONObject colorString = cardData.getJSONObject("color");
        Color color = new Color(colorString.getFloat("r"), colorString.getFloat("g"), colorString.getFloat("b"),
                colorString.getFloat("a"));

        // Try to get the Resource info, and if it does not exist, try to to get action data instead.
        String cardText = "";
        if (cardData.has("resource")) {
            JSONObject resource = cardData.getJSONObject("resource");
            cardText += resource.getString("type") + " " + resource.getInt("amount");
        } else if(cardData.has("action")) {
            JSONObject action = cardData.getJSONObject("action");
            // TODO interperit action data 
            cardText += "action";
        }

        Button card = new DrawableCard(skin, rotation, color);
        card.add(new Label(cardText, skin));
        return card;
    }
}
