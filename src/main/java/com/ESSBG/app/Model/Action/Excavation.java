package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.List;

public class Excavation implements IEitherAction {

    List<Resource> resources;

    public Excavation(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public Resource getItem(int i) {
        return null;
    }

    @Override
    public int size(){
        return resources.size();
    }

}
