package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class ReduceNeighborResources implements INeighborReduction {

    private final List<ResourceEnum> neighborReductions;

    public ReduceNeighborResources(List<ResourceEnum> neighborReductions) {
        this.neighborReductions = neighborReductions;
    }

    @Override
    public List<ResourceEnum> getList() {
        List<ResourceEnum> copy = new ArrayList<>();
        for (ResourceEnum r : neighborReductions){
            copy.add(r);
        }
        return copy;
    }


}
