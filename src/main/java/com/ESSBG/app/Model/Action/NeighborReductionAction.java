package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sebastian Selander
 * 
 * An action that reduces the cost of specified resources from neighbors.
 */
public class NeighborReductionAction implements INeighborAction {

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
        List<ResourceEnum> copy = new ArrayList<>(neighborReductions);
        return copy;
    }


}
