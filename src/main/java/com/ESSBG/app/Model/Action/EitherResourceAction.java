package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Selander
 * 
 * An action that gives a given set of resources where only one can be chosen from per turn.
 */
public class EitherResourceAction implements IEitherAction {

    private final List<ResourceEnum> resources;

    /**
     * Constructor for EitherResource
     * @param resources
     * @return EitherResourceAction
     */
    public EitherResourceAction(List<ResourceEnum> resources) {
        this.resources = new ArrayList<>(resources);
    }

    /**
     * Getter for resources
     * @return List<ResourceEnum>
     */
    @Override
    public List<ResourceEnum> getList() {
        List<ResourceEnum> copy = new ArrayList<>(resources);
        return copy;
    }
}
