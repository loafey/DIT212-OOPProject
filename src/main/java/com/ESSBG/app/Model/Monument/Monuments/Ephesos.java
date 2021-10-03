package com.ESSBG.app.Model.Monument.Monuments;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class Ephesos extends Monument {

    public Ephesos() {
        super("Ephesos", ResourceEnum.PAPYRUS);
    }


    @Override
    public List<ResourceEnum> stage2Reward() {
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
