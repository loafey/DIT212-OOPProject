package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public abstract class Card {
    private final String name;
    private final List<ResourceEnum> cost;
    private final ColorEnum color;

    public Card(String name,List<ResourceEnum> cost, ColorEnum color) {
        this.name = name;
        this.cost = cost;
        this.color = color;
    }


    public List<ResourceEnum> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
