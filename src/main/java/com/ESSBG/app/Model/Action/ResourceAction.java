package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceAction implements IResource {

    private final List<Resource> resources;

    public ResourceAction(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public List<Resource> getResources() {
        return new ArrayList<>(resources);
    }
}
