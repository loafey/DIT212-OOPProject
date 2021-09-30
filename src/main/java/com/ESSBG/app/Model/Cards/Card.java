package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class Card<T> {
    private final String name;
    private final List<ResourceEnum> cost;
    private final ColorEnum color;
    private final T action;   //placeholder, need to find a way to generalise all actions

    public Card(String name,List<ResourceEnum> cost, ColorEnum color, T action) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.action = action;
    }

    public T getAction() {
        return action;
    }

    public List<ResourceEnum> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
