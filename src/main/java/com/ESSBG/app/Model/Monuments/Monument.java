package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Action.EitherResource;
import com.ESSBG.app.Model.Action.IResource;
import com.ESSBG.app.Model.Resource;

import java.util.ArrayList;
import java.util.List;

public abstract class Monument implements IMonument {

    private final String name;
    private final Resource startingResource; /** The starting resource given to the player (top left corner on monument)*/
    private int stageBuilt = 0; /** How many stages that have been built yet on the monument*/


    //private final List<IResource> monAction; //placeholder, need to find a way to generalise all actions
    //private int monUnlock;


    public Monument(String name, Resource startingResource) {
        this.name = name;
        this.startingResource = startingResource;
    }

    public String getName() {
        return name;
    }

    public void buildStage(){
        stageBuilt++;
    }

    public int getStageBuilt() {
        return stageBuilt;
    }

    public Resource getStartingResource() {
        return startingResource;
    }

    // Ska ändra så det är mer generellt
    public abstract EitherResource level2Reward();

    public abstract List<Resource> resourcesToBuildStage1();

    public abstract List<Resource> resourcesToBuildStage2();

    public abstract List<Resource> resourcesToBuildStage3();
}