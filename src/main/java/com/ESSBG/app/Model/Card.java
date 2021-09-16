package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private final List<IResource> cost;
    private final ColorEnum color;
    private final GameAction action;

    public Card(List<IResource> cost, ColorEnum color, GameAction action) {
        this.cost = cost;
        this.color = color;
        this.action = action;
    }
}
