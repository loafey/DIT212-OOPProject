package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class NeighborReductionAction implements INeighborReductionAction {

    private final List<ResourceEnum> neighborReductions;

    /**
     * Constructor for NeighborReductionAction
     * @param neighborReductions
     */
    public NeighborReductionAction(List<ResourceEnum> neighborReductions) {
        this.neighborReductions = neighborReductions;
    }

    /**
     * Getter for neighborReductions
     * @return List<ResourceEnum>
     */
    @Override
    public List<ResourceEnum> getList() {
        List<ResourceEnum> copy = new ArrayList<>();
        for (ResourceEnum r : neighborReductions){
            copy.add(r);
        }
        return copy;
    }


}
