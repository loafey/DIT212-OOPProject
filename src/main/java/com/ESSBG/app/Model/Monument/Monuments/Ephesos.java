package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

public class Ephesos extends Monument {

    public Ephesos(Player player) {
        super("Ephesos",player, ResourceEnum.PAPYRUS);
    }


    @Override
    public void stage2Reward() {
        int coins = player.getState().getCoins();
        coins+=9;
        player.getState().setCoins(coins);
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
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.PAPYRUS, 2);
    }
}
