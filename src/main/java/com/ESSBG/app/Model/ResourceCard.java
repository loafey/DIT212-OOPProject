package com.ESSBG.app.Model;

import java.util.HashMap;

public class ResourceCard extends AbstractCard {

    private String type;
    private int amount;

    public ResourceCard(HashMap<String, Integer> cost, ColorEnum color, String type, int amount) {
        super(cost, color);
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
