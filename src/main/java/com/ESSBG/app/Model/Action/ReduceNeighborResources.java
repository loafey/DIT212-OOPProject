package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ReduceNeighborResources implements INeighborReduction<Resource> {

    private final int reduceTo;
    private final List<Resource> leftNeighbor;
    private final List<Resource> rightNeighbor;

    public ReduceNeighborResources(int reduceTo, List<Resource> leftNeighbor, List<Resource> rightNeighbor) {
        this.reduceTo = reduceTo;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
    }

    @Override
    public List<Resource> getLeftNeighbor() {
        return new ArrayList<>(leftNeighbor);
    }

    @Override
    public int reduceTo() {
        return reduceTo;
    }

    @Override
    public List<Resource> getRightNeighbor() {
        return new ArrayList<>(rightNeighbor);
    }
}
