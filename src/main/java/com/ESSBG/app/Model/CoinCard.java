package com.ESSBG.app.Model;

import java.util.Map;

public class CoinCard extends AbstractCard {

    boolean hasGiven;
    int amount;

    public CoinCard(Map<String, Integer> cost, ColorEnum color, int amount) {
        super(cost, color);
        hasGiven = false;
        this.amount = amount;
    }

    private void setHasGiven(boolean hasGiven) {
        this.hasGiven = hasGiven;
    }

    public int getCoins(){
        if (!hasGiven) {
            hasGiven = true;
            return amount;
        }
        else return 0;      //maybe this is an ugly way of doing it
    }
}
