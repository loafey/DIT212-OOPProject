package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class ReduceNeighborResources implements INeighborReduction {

    private final List<ResourceEnum> leftNeighbor;
    private final List<ResourceEnum> rightNeighbor;

    public ReduceNeighborResources(List<ResourceEnum> leftNeighbor, List<ResourceEnum> rightNeighbor) {
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
    }

    @Override
    public List<ResourceEnum> getLeftNeighborReductions() {
        return new ArrayList<>(leftNeighbor);
    }

    @Override
    public List<ResourceEnum> getRightNeighborReductions() {
        return new ArrayList<>(rightNeighbor);
    }
}
