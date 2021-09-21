package com.ESSBG.app.Model;

import java.util.HashMap;
import java.util.List;

public class ReduceNeighborCard extends AbstractCard {

    boolean left;
    boolean right;
    List<String> reduceTypes;

    public ReduceNeighborCard(HashMap<String, Integer> cost, ColorEnum color, boolean left, boolean right, List<String> reduceTypes) {
        super(cost, color);
        this.left = left;
        this.right = right;
        this.reduceTypes = reduceTypes;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public List<String> getReduceTypes() {
        return reduceTypes;
    }
}
