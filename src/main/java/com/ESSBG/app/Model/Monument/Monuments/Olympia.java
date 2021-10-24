package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Emmie Berger
 *
 * A class that represents the Monument of Olympia
 */

public class Olympia extends Monument {

    public Olympia() {
        super("Olympia", ResourceEnum.WOOD);
    }



    @Override
    public List<ResourceEnum> getStage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();

        int k=0;
        while (k<9) {
            newResources.add(ResourceEnum.COIN);
            k++;
        }

        return newResources;
    }

    @Override
    protected void initResourcesToBuildStage1() {
        resourcesToBuildStage1 = super.initializeResources(ResourceEnum.WOOD, 2);
    }

    @Override
    protected void initResourcesToBuildStage2() {
        resourcesToBuildStage2 = super.initializeResources(ResourceEnum.STONE, 2);
    }

    @Override
    protected void initResourcesToBuildStage3() {
        resourcesToBuildStage3 = super.initializeResources(ResourceEnum.ORE, 2);
    }
}
