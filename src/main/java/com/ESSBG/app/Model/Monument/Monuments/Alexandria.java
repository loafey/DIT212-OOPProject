package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class Alexandria extends Monument {

    public Alexandria(Player player) {
        super("Alexandria",player, ResourceEnum.GLASS);
    }


    /**
     * Add Alexandria's stage 2 reward to it's player's list of either resources
     */

    @Override
    public void stage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();
        newResources.add(ResourceEnum.CLAY);
        newResources.add(ResourceEnum.ORE);
        newResources.add(ResourceEnum.WOOD);
        newResources.add(ResourceEnum.STONE);

        List<ArrayList<ResourceEnum>> currentEitherResources = player.getState().getEitherResources();
        currentEitherResources.add(newResources);
        player.getState().setEitherResources(currentEitherResources);     //refactor this to be immutable-ish
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
