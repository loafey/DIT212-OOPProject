package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class ResourceAction implements IResourceAction {

    private final List<ResourceEnum> resources;

    /**
     * Constructor for resources
     * @param resources
     */
    public ResourceAction(List<ResourceEnum> resources) {
        this.resources = resources;
    }

    /**
     * Getter for resources
     * @return List<ResourceEnum>
     */
    @Override
    public List<ResourceEnum> getList() {
        List<ResourceEnum> copy = new ArrayList<>();
        copy.addAll(resources);
        return copy;
    }
}
