package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.IAction;
import com.ESSBG.app.Model.ColorEnum;
import com.ESSBG.app.Model.IBuyable;
import com.ESSBG.app.Model.Resource;

import javax.swing.*;
import java.util.List;

public class Card implements IBuyable {
    private String name;
    private List<Resource> cost;
    private ColorEnum color;
    private IAction action;

    public Card(String name,List<Resource> cost, ColorEnum color, IAction action) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.action = action;
    }

    public IAction getAction() {
        return action;
    }

    public List<Resource> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
