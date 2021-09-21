package com.ESSBG.app.Model;

import java.util.Map;

public abstract class AbstractCard {

    Map<String, Integer> cost;
    ColorEnum color;

    public AbstractCard(Map<String, Integer> cost, ColorEnum color) {
        this.cost = cost;
        this.color = color;
    }

    public ColorEnum getColor() {
        return color;
    }

    public Map<String, Integer> getCost() {
        return cost;
    }
}
