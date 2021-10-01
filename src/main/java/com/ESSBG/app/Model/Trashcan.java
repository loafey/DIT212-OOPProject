package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Trashcan {
    List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public Card takeCard(int i){
        return cards.remove(i);
    }

    public void addCard(Card card){
        cards.add(card);
    }
}
