package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class ReduceNeighborResources implements INeighborReduction {

    private final int reduceTo;
    private final List<ResourceEnum> leftNeighbor;
    private final List<ResourceEnum> rightNeighbor;

    public ReduceNeighborResources(int reduceTo, List<ResourceEnum> leftNeighbor, List<ResourceEnum> rightNeighbor) {
        this.reduceTo = reduceTo;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
    }

    @Override
    public List<ResourceEnum> getLeftNeighbor() {
        return new ArrayList<>(leftNeighbor);
    }

    @Override
    public int reduceTo() {
        return reduceTo;
    }

    @Override
    public List<ResourceEnum> getRightNeighbor() {
        return new ArrayList<>(rightNeighbor);
    }
}
