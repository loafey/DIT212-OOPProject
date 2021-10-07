package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    public static List<List<Card>> getPeriodCards(){
        return null;
    }

    /**
     * Initializes all cards in the game for a specific age
     * @param age The current age (1-3)
     * @param playerAmount The amount of players in the game
     * @param handSize The size of a hand
     * @return all cards
     */
    public static List<List<Card>> generateHands(int age, int playerAmount, int handSize) {
        ArrayList<List<Card>> allCards = new ArrayList<>(playerAmount);
        for (int i = 0; i < playerAmount; i++){
            ArrayList<Card> hand = new ArrayList<>(handSize);
            for (int j = 0; j < handSize; j++){
                hand.add(generateCard(age));
            }
        }
        return allCards;
    }

    /**
     * Generates a random card during a specific age
     * @param age The current age
     * @return A random card
     */
    //TODO generate more random cards
    public static Card generateCard(int age) {
        List<ResourceEnum> list = new ArrayList<>();
        list.add(ResourceEnum.WOOD);
        list.add(ResourceEnum.WOOD);
        return new ResourceActionCard("Sawmill", null, ColorEnum.BROWN, new ResourceAction(list));
    }
}
