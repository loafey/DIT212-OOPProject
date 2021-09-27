package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Action.EitherResource;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public abstract class Monument implements IMonument {

    private final String name;
    protected final Player player;
    private final ResourceEnum startingResource; /** The starting resource given to the player (top left corner on monument)*/
    private int stageBuilt = 0; /** How many stages that have been built yet on the monument*/
    protected List<ResourceEnum> resourcesToBuildStage1;
    protected List<ResourceEnum> resourcesToBuildStage2;
    protected List<ResourceEnum> resourcesToBuildStage3;


    //private final List<IResource> monAction; //placeholder, need to find a way to generalise all actions
    //private int monUnlock;


    public Monument(String name, Player player, ResourceEnum startingResource) {
        this.name = name;
        this.player = player;
        this.startingResource = startingResource;
        init();
    }

    private void init(){
        givePlayerStartingResource();
        initResourcesToBuildStage1();
        initResourcesToBuildStage2();
        initResourcesToBuildStage3();
    }

    /**
     * Adds the starting resource given by their monument to a player
     * if their list of resources is empty. Otherwise throw an exception.
     */
    protected void givePlayerStartingResource(){
        List<ResourceEnum> list = player.getState().getGuaranteedResources();

        if (list.isEmpty()) {
            list.add(startingResource);
            player.getState().setGuaranteedResources(list);
        }
        else{
            throw new IllegalStateException("The player's resources should be empty when they are given their starting resource");
        }
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

    public ResourceEnum getStartingResource() {
        return startingResource;
    }

    // Ska ändra så det är mer generellt
    public abstract void stage2Reward();

    public List<ResourceEnum> resourcesToBuildAStage(ResourceEnum resource, int numberOfUnits){
        List<ResourceEnum> list = new ArrayList<>();
        for (int i=0; i<numberOfUnits; i++){
            list.add(resource);
        }
        return list;
    }

    /**
     * To be used in subclasses to initalize all stage's requirements for building
     * @param resource The resource required to build a certain stage
     * @param amount How many units required of the resource
     * @return
     */

    protected List<ResourceEnum> initializeResources(ResourceEnum resource, int amount){
        List<ResourceEnum> list = new ArrayList<>();
        for (int i=0; i<amount; i++){
            list.add(resource);
        }
        return list;
    }


    protected abstract void initResourcesToBuildStage1();
    protected abstract void initResourcesToBuildStage2();
    protected abstract void initResourcesToBuildStage3();


}