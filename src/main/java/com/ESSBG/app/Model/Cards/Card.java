package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.ColorEnum;
import com.ESSBG.app.Model.IBuyable;
import com.ESSBG.app.Model.Resource;

import java.util.List;

public class Card implements IBuyable {
    private String name;
    private List<Resource> cost;
    private ColorEnum color;
    private IEitherAction action;   //placeholder, need to find a way to generalise all actions

    public Card(String name,List<Resource> cost, ColorEnum color, IEitherAction action) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.action = action;
    }

    public IEitherAction getAction() {
        return action;
    }

    public List<Resource> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
