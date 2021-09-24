package com.ESSBG.app.Model;

import com.ESSBG.app.Model.ColorEnum;

import javax.swing.*;
import java.util.List;

public class Card {
    private String name;
    private List<Resource> cost;
    private ColorEnum color;
    private Action action;

    public Card(List<Resource> cost, ColorEnum color, Action action, String name) {
        this.cost = cost;
        this.color = color;
        this.action = action;
        this.name = name;
    }

    public List<Resource> getCost() {
        return cost;
    }

    public ColorEnum getColor() {
        return color;
    }
}
