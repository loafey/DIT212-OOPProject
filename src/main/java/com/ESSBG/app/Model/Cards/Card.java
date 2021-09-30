package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IAction;

import java.util.List;

public class Card {
    private final String name;
    private final List<ResourceEnum> cost;
    private final ColorEnum color;
    private final IAction action;   //placeholder, need to find a way to generalise all actions

    public Card(String name,List<ResourceEnum> cost, ColorEnum color, IAction action) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.action = action;
    }

    public IAction getAction() {
        return action;
    }

    public List<ResourceEnum> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
