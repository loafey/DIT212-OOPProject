package com.ESSBG.app.Model.Cards;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    public static List<List<Card>> getPeriodCards(){
        return null;
    }

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

    public static Card generateCard(int age) {
        return null;
    }
}
