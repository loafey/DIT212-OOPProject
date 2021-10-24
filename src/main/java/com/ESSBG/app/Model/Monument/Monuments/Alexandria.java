package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Emmie Berger
 *
 * A class that represents the Monument of Alexandria
 */
public class Alexandria extends Monument {

    public Alexandria() {
        super("Alexandria",ResourceEnum.GLASS);
    }


    @Override
    public List<ResourceEnum> getStage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();
        newResources.add(ResourceEnum.CLAY);
        newResources.add(ResourceEnum.ORE);
        newResources.add(ResourceEnum.WOOD);
        newResources.add(ResourceEnum.STONE);

        return newResources;
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(ResourceEnum.WOOD, 2);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.GLASS, 2);
    }


}
