package com.ESSBG.app.Model;

import java.util.List;

public class Trashcan {
    List<Card> cards;

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
