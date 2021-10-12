package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborReductionAction;

public class NeighborReductionCard extends Card {

    private INeighborReductionAction action;

    /**
     * Constructor for NeighborReductionCard
     * @param name
     * @param cost
     * @param color
     * @param action
     */
    public NeighborReductionCard(String name, List<ResourceEnum> cost, ColorEnum color, INeighborReductionAction action) {
        super(name, cost, color, CardTypeEnum.NEIGHBORREDUCTION);
        this.action = action;

    }

    /**
     * Getter for action. INeighborReduction represents an action that returns resources to reduce the cost of 
     * @return INeighborReduction
     */
    public INeighborReductionAction getAction(){
        return action;
    }
    
}
