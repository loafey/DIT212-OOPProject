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
    public List<Resource> resourcesToBuildStage1() {
        List<Resource> list = new ArrayList<>();
        list.add(Resource.STONE);
        list.add(Resource.STONE);
        return list;
    }

    @Override
    public List<Resource> resourcesToBuildStage2() {
        List<Resource> list = new ArrayList<>();
        list.add(Resource.WOOD);
        list.add(Resource.WOOD);
        return list;
    }

    @Override
    public List<Resource> resourcesToBuildStage3() {
        List<Resource> list = new ArrayList<>();
        list.add(Resource.GLASS);
        list.add(Resource.GLASS);
        return list;
    }
}
