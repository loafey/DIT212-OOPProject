package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class EitherResource implements IEitherAction<Resource> {

    private final List<Resource> resources;

    public EitherResource(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public List<Resource> getEithers() {
        return new ArrayList<>(resources);
    }
}
