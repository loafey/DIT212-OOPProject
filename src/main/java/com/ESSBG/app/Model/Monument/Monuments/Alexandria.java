package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class Alexandria extends Monument {

    public Alexandria() {
        super("Alexandria",ResourceEnum.GLASS);
    }


    /**
     * Add Alexandria's stage 2 reward to it's player's list of either resources
     * @return
     */

    @Override
    public List<ResourceEnum> stage2Reward() {
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
