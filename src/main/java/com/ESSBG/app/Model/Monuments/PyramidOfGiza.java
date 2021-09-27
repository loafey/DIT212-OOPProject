package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public class PyramidOfGiza extends Monument{

    public PyramidOfGiza(Player player) {
        super("Pyramid of Giza", player, Resource.STONE);
    }

    @Override
    public void stage2Reward() {
        player.setPeacePoints(player.getPeacePoints() + 5);
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.resourcesToBuildAStage(Resource.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.resourcesToBuildAStage(Resource.WOOD, 3);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.resourcesToBuildAStage(Resource.STONE, 4);
    }


}
