package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

public class PyramidOfGiza extends Monument{

    public PyramidOfGiza(Player player) {
        super("Pyramid of Giza", player, ResourceEnum.STONE);
    }

    @Override
    public void stage2Reward() {
        //player.setPeacePoints(player.getPeacePoints() + 5); //points are guaranteed resources, change
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.resourcesToBuildAStage(ResourceEnum.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.resourcesToBuildAStage(ResourceEnum.WOOD, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.resourcesToBuildAStage(ResourceEnum.STONE, 4);
    }


}
