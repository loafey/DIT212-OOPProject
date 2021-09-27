package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Action.EitherResource;
import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class Alexandria extends Monument{

    public Alexandria(Player player) {
        super("Alexandria",player, Resource.GLASS);
    }


    /**
     * Add Alexandria's stage 2 reward to it's player's list of either resources
     */

    @Override
    public void stage2Reward() {
        List<Resource> newResources = new ArrayList<>();
        newResources.add(Resource.CLAY);
        newResources.add(Resource.ORE);
        newResources.add(Resource.WOOD);
        newResources.add(Resource.STONE);

        List<List<Resource>> currentEitherResources = player.getEitherResources();
        currentEitherResources.add(newResources);
        player.setEitherResources(currentEitherResources);
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(Resource.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(Resource.WOOD, 2);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(Resource.GLASS, 2);
    }


}
