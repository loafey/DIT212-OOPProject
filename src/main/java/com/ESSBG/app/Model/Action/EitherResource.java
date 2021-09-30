package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class EitherResource implements IEitherAction {

    private final List<ResourceEnum> resources;

    public EitherResource(List<ResourceEnum> resources) {
        this.resources = new ArrayList<>(resources);
    }

    @Override
    public List<ResourceEnum> getList() {
        List<ResourceEnum> copy = new ArrayList<>();
        copy.addAll(resources);
        return copy;
    }

    
}
