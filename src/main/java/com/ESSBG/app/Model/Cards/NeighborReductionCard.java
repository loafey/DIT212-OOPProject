package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborReduction;

public class NeighborReductionCard extends Card {

    private INeighborReduction action;

    public NeighborReductionCard(String name, List<ResourceEnum> cost, ColorEnum color) {
        super(name, cost, color);

    }

    public INeighborReduction getAction(){
        return action;
    }
    
}
