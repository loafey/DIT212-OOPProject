package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Emmie Berger
 *
 * A class that represents the Monument of Halikarnassos
 */

public class Halikarnassos extends Monument {

    public Halikarnassos() {
        super("Halikarnassos", ResourceEnum.TEXTILE);
    }

    // Modified, not how it's actually in the real game
    @Override
    public List<ResourceEnum> getStage2Reward() {
        ArrayList<ResourceEnum> newResources = new ArrayList<>();

        int k=0;
        while (k<5) {
            newResources.add(ResourceEnum.POINT);
            k++;
        }

        return newResources;
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
