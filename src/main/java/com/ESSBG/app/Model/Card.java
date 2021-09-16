package com.ESSBG.app.Model;

import jdk.internal.loader.Resource;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private final List<Resource> cost;
    private final ColorEnum color;
    private final GameAction action;

    public Card(List<Resource> cost, ColorEnum color, GameAction action) {
        this.cost = cost;
        this.color = color;
        this.action = action;
    }
}
