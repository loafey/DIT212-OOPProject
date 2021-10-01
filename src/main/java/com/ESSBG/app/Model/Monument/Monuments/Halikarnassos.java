package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class Halikarnassos extends Monument {

    public Halikarnassos(Player player) {
        super("Halikarnassos",player, ResourceEnum.TEXTILE);
    }

    // Modified, not how it's actually in the real game
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
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.CLAY, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(ResourceEnum.ORE, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.TEXTILE, 2);
    }
}
