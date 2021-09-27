package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class ResourceAction implements IResourceAction {

    private final List<ResourceEnum> resources;

    public ResourceAction(List<ResourceEnum> resources) {
        this.resources = resources;
    }

    @Override
    public List<ResourceEnum> getResources() {
        return new ArrayList<>(resources);
    }
}
