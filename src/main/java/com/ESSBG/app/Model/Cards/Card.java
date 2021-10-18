package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IAction;

import java.util.List;

/**
 * Author: Sebastian Selander, Emmie Berger
 * 
 * An abstract class that represent a card.
 */
public abstract class Card {
    private final String name;
    private final List<ResourceEnum> cost;
    private final ColorEnum color;
    private final CardTypeEnum cardTypeEnum;

    /**
     * Constructor for the abstract class Card
     * @param name
     * @param cost
     * @param color
     */
    public Card(String name,List<ResourceEnum> cost, ColorEnum color, CardTypeEnum cardTypeEnum) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.cardTypeEnum = cardTypeEnum;
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

    /**
     * Getter for CardTypeEnum
     * @return CardTypeEnum
     */
    public CardTypeEnum getCardTypeEnum() {
        return cardTypeEnum;
    }

    /**
     * Get the action the card holds. The information about what action it is is lost, but not necessary for the Game class
     * @return IAction
     */
    public abstract IAction getAction();


}
