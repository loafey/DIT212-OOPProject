package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class Giza extends Monument{

    public Giza(Player player) {
        super("ThePyramidsOfGiza",player, ResourceEnum.STONE);
    }

    @Override
    public void stage2Reward() {
        List<ResourceEnum> guaranteedResources = player.getState().getGuaranteedResources();
        for (int i=0; i<5; i++) {
            guaranteedResources.add(ResourceEnum.POINT);
        }
        player.getState().setGuaranteedResources(guaranteedResources);
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.WOOD, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.STONE, 4);
    }
}
