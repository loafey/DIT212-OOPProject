package com.ESSBG.app.Model.Actions;

import java.util.List;

public class ReductionAction implements IGameAction{

    boolean reduceRightNeighbor;
    boolean reduceLeftNeighbor;
    List<ResourceAction> resources;

    public ReductionAction(boolean reduceRightNeighbor, boolean reduceLeftNeighbor, List<ResourceAction> resources) {
        this.reduceRightNeighbor = reduceRightNeighbor;
        this.reduceLeftNeighbor = reduceLeftNeighbor;
        this.resources = resources;
    }

    @Override
    public Boolean isEndGameAction() {
        return false;
    }
}
