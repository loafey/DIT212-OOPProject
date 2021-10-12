package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborReductionAction;

public class NeighborReductionCard extends Card {

    private INeighborReductionHandler handler;

    /**
     * Constructor for NeighborReductionCard
     * @param name
     * @param cost
     * @param color
     * @param handler
     */
    public NeighborReductionCard(String name, List<ResourceEnum> cost, ColorEnum color, INeighborReductionHandler handler) {
        super(name, cost, color, CardTypeEnum.NEIGHBORREDUCTION);
        this.handler = handler;

    }


    
}
