package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class Babylon extends Monument {

    public Babylon(Player player) {
        super("Babylon",player, ResourceEnum.CLAY);
    }

    @Override
    public void stage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();
        newResources.add(ResourceEnum.Laboratory);
        newResources.add(ResourceEnum.Library);
        newResources.add(ResourceEnum.Dispensary);

        List<ArrayList<ResourceEnum>> currentEitherResources = player.getState().getEitherResources();
        currentEitherResources.add(newResources);
        player.getState().setEitherResources(currentEitherResources);     //refactor this to be immutable-ish
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.CLAY, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(ResourceEnum.WOOD, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.CLAY, 4);
    }
}
