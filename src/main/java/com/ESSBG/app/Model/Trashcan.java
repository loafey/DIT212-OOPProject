package com.ESSBG.app.Model;

import java.util.List;

public class Trashcan {
    List<AbstractCard> cards;

    public List<AbstractCard> getCards() {
        return cards;
    }

    public AbstractCard takeCard(int i){
        return cards.remove(i);
    }

    public void addCard(AbstractCard card){
        cards.add(card);
    }
}
