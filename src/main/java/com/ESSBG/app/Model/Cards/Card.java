package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.ColorEnum;
import com.ESSBG.app.Model.IBuyable;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class Card implements IBuyable {
    private String name;
    private List<ResourceEnum> cost;
    private ColorEnum color;
    private IEitherAction action;   //placeholder, need to find a way to generalise all actions

    public Card(String name,List<ResourceEnum> cost, ColorEnum color, IEitherAction action) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.action = action;
    }

    public IEitherAction getAction() {
        return action;
    }

    public List<ResourceEnum> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
