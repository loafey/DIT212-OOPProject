package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.HashMap;
import java.util.List;

public class ReduceNeighborResources implements INeighborAction {

    private final List<Resource> resources;
    private final boolean leftNeighbor;
    private final boolean rightNeighbor;

    public ReduceNeighborResources(List<Resource> resources, boolean leftNeighbor, boolean rightNeighbor) {
        this.resources = resources;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
    }

    @Override
    public boolean rightNeighbor() {
        return this.rightNeighbor;
    }

    @Override
    public boolean leftNeighbor() {
        return this.leftNeighbor;
    }

    @Override
    public List<Resource> resources() {
        return resources;
    }

    @Override
    public void doAction(Player p) {
        HashMap<Resource, Player> reductions = p.getReductions();
        for (Resource r : resources) {
            if (leftNeighbor) reductions.put(r, p.getLeftPlayer());
            if (rightNeighbor) reductions.put(r, p.getRightPlayer());
        }
        p.setReductions(reductions);
    }
}
