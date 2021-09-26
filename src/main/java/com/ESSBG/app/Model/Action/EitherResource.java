package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.List;

public class EitherResource implements IEitherAction {

    private List<Resource> resources;

    public EitherResource(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public void doAction(Player p) {
        List<Resource> addResources = p.getEitherResources();
        addResources.addAll(resources);
        p.setGuaranteedResources(addResources);
    }
}
