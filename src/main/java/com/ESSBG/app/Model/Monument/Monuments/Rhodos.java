package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class Rhodos extends Monument {

    public Rhodos(Player player) {
        super("Rhodos",player, ResourceEnum.ORE);
    }

    @Override
    public void stage2Reward() {
        int warTokens = player.getState().getWarTokens();
        warTokens+=2;
        player.getState().setWarTokens(warTokens);
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.WOOD, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(ResourceEnum.CLAY, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.ORE, 4);
    }
}
