package com.ESSBG.app.Model;

import java.util.HashMap;

public abstract class AbstractCard {

    HashMap<String, Integer> cost;
    ColorEnum color;

    public AbstractCard(HashMap<String, Integer> cost, ColorEnum color) {
        this.cost = cost;
        this.color = color;
    }

    public ColorEnum getColor() {
        return color;
    }

    public HashMap<String, Integer> getCost() {
        return cost;
    }
}
