package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.Player.Player;
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

        if (resourcesToBuildStage1 == null || resourcesToBuildStage2 == null || resourcesToBuildStage3 == null){
            throw new IllegalStateException("Monument " + name + " hasn't been created properly. Missing cost for building at least one state");
        }
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
        if (stageBuilt < 3) {
            stageBuilt++;
        }
        else {
            throw new IllegalStateException("Cannot build another stage when monument is already at stage 3");
        }
    }

    public int getStageBuilt() {
        return stageBuilt;
    }

    public ResourceEnum getStartingResource() {
        return startingResource;
    }



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




    // Has to be specified for each monument
    public abstract void stage2Reward();
    protected abstract void initResourcesToBuildStage1();
    protected abstract void initResourcesToBuildStage2();
    protected abstract void initResourcesToBuildStage3();


}