package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Action.EitherResource;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class Alexandria extends Monument{
    List<Resource> resources = new ArrayList<>();
    EitherResource eitherResources;

    public Alexandria() {
        super("Alexandria", Resource.GLASS);
        resources.add(Resource.CLAY);
        resources.add(Resource.ORE);
        resources.add(Resource.WOOD);
        resources.add(Resource.STONE);
        eitherResources = new EitherResource(resources);
    }


    @Override
    public EitherResource level2Reward() {
        return eitherResources;
    }
}
