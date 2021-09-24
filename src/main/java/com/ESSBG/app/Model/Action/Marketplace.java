package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.List;

public class Marketplace implements INeighboraction {

    List<Resource> resources;
    boolean leftNeighbor;
    boolean rightNeighbor;
    int reduce;

    public Marketplace(List<Resource> resources, boolean leftNeighbor, boolean rightNeighbor, int reduce) {
        this.resources = resources;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.reduce = reduce;
    }

    @Override
    public boolean rightNeighbor() {
        return false;
    }

    @Override
    public boolean leftNeighbor() {
        return false;
    }

    @Override
    public List<Resource> resources() {
        return null;
    }
}
