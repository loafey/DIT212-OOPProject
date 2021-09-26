package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceAction implements IResourceAction {

    private List<Resource> resources;

    public ResourceAction(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public void doAction(Player p) {
        List<Resource> addResources = p.getGuaranteedResources();
        addResources.addAll(resources);
        p.setGuaranteedResources(addResources);
    }
}
