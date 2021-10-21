package com.ESSBG.app.Render.GameScene.Elements;

import com.ESSBG.app.Render.GameScene.GameController;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.Sys;

public class DrawableBoard {
    private Skin skin;
    private Table handTable;
    private Table placedCards;
    private Table monument;

    private GameController controller;

    public DrawableBoard(GameController controller, Skin skin, Table handTable, Table placedCards, Table monument){
        this.skin = skin;
        this.handTable = handTable;
        this.placedCards = placedCards;
        this.monument = monument;
        this.controller = controller;
    }
    
    /**
     * Updates the board 
     * @param data The data to be displayed
     * @param controller A GameController so the buttons can be linked up to appropriate actions when clicked.
     * @param skin The skin to be used
     * @param handTable The table where cards in your hand should be displayed
     * @param placedCards The table where cards you have placed should be displayed
     * @param monument The table for the monument
     */
    public void updateBoard(JSONObject data) {
        updateHand(skin, controller, handTable, data.getJSONArray("handCards"));
        updatePlacedCards(skin, placedCards, data.getJSONArray("placedCards"));
        updateResources(skin, monument, data.getJSONObject("resources"));
        updateMonument(skin, monument, data.getJSONObject("monument"));
        //var leftNeighbourJson = data.getJSONObject("leftNeighbour");
        //var rightNeighbourJson = data.getJSONObject("rightNeighbour");
    }

    public void hideHandCards(){
        handTable.clear();
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

            JSONArray monumentContent = (JSONArray)i;
            HashMap<Object, Integer> algo = new HashMap<>();

            for (Object c : monumentContent) {
                if (algo.containsKey(c)) {
                    int val = algo.get(c) + 1;
                    algo.replace(c, val);
                } else {
                    algo.put(c, 1);
                }
            }
            String content = "";
            for (Entry<Object, Integer> val : algo.entrySet()) {
                content += val.getKey().toString() + ": " + val.getValue().toString(); 
            }

            Button b = new Button(skin);
            b.add(new Label(content,skin));
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
            Button card = GenerateCard(skin, (JSONObject) cardData, 0f, false);
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
            Button card = GenerateCard(skin, (JSONObject) cardData, -5f, true);
            handTable.add(card).width(84).height(128);

            gameController.assignCardButton(card, index);
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
    private Button GenerateCard(Skin skin, JSONObject cardData, Float rotation, boolean displayCost) {
        JSONObject colorString = cardData.getJSONObject("color");
        Color color = new Color(colorString.getFloat("r"), colorString.getFloat("g"), colorString.getFloat("b"),
                colorString.getFloat("a"));

        // Try to get the Resource info, and if it does not exist, try to to get action data instead.
        boolean neighbourReduction = cardData.getString("cardType").equals("NEIGHBORREDUCTION");
        boolean eitherAction = cardData.getString("cardType").equals("EITHERACTION");
        String cardText = "";
        if (cardData.has("resource")) {
            JSONArray resource = cardData.getJSONArray("resource");
            for (Object r : resource) {
                if (neighbourReduction) cardText += "<";
                cardText += (String)r + (eitherAction ? "/" : "");
                if (neighbourReduction) cardText += ">";
                cardText += "\n";
            }
        } else if(cardData.has("action")) {
            JSONObject action = cardData.getJSONObject("action");
            cardText += "action";
        }

        if (displayCost && !cardData.getJSONArray("cost").isEmpty()){
            cardText += "\nCost:\n";
            for (Object c : cardData.getJSONArray("cost")){
                cardText += ((String) c) + "\n";
            }
        }

        Button card = new DrawableCard(skin, rotation, color);
        Label cardLabel = new Label(cardText, skin);
        cardLabel.setFontScale(0.5f, 0.5f);
        card.add(cardLabel);
        return card;
    }
}
