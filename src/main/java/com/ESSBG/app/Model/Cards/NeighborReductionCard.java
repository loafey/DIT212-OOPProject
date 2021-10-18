package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborAction;

/**
 * Author: Sebastian Selander
 * 
 * A concrete card that holds a neighbor action.
 */
public class NeighborReductionCard extends Card {

    private INeighborAction action;

    /**
     * Constructor for NeighborReductionCard
     * @param name
     * @param cost
     * @param color
     * @param action
     */
    public NeighborReductionCard(String name, List<ResourceEnum> cost, ColorEnum color, INeighborAction action) {
        super(name, cost, color, CardTypeEnum.NEIGHBORREDUCTION);
        this.action = action;

    }

    /**
     * Getter for action. INeighborReduction represents an action that returns resources to reduce the cost of 
     * @return INeighborReduction
     */
    public INeighborAction getAction(){
        return action;
    }
    
}
