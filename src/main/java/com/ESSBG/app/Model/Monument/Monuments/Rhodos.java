package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Emmie Berger
 *
 * A class that represents the Monument of Rhodos
 */

public class Rhodos extends Monument {


    public Rhodos() {
        super("Rhodos", ResourceEnum.ORE);
    }

    @Override
    public List<ResourceEnum> getStage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();
        newResources.add(ResourceEnum.WAR);
        newResources.add(ResourceEnum.WAR);

        return newResources;
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
