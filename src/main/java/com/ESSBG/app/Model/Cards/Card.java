package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public abstract class Card {
    private final String name;
    private final List<ResourceEnum> cost;
    private final ColorEnum color;

    /**
     * Constructor for the abstract class Card
     * @param name
     * @param cost
     * @param color
     */
    public Card(String name,List<ResourceEnum> cost, ColorEnum color) {
        this.name = name;
        this.cost = cost;
        this.color = color;
    }

    /**
     * Getter for name
     * @return String
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for cost
     * @return List<ResourceEnum>
     */
    public List<ResourceEnum> getCost() {
        return cost;
    }

    /**
     * Getter for color
     * @return ColorEnum
     */
    public ColorEnum getColor() {
        return color;
    }
}
