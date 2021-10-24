package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Emmie Berger
 *
 * A class that represents a monument in the game
 */


public abstract class Monument {
    /**
     * The name of this monument
     */
    private final String name;

    /**
     * The starting resource given to the player (top left corner on monument)
     */
    private final ResourceEnum startingResource;
    /**
     * How many stages that have been built yet on the monument (max 3)
     */
    private int stageBuilt = 0;

    /**
     * The resources required to build stage 1 of this monument
     */
    protected List<ResourceEnum> resourcesToBuildStage1;

    /**
     * The resources required to build stage 2 of this monument
     */
    protected List<ResourceEnum> resourcesToBuildStage2;

    /**
     * The resources required to build stage 3 of this monument
     * */
    protected List<ResourceEnum> resourcesToBuildStage3;

    /**
     * Creates a Monument with a certain name and what starting resources it will give a player that has it.
     *
     * @param name
     * @param startingResource
     */
    public Monument(String name, ResourceEnum startingResource) {
        this.name = name;
        this.startingResource = startingResource;
        init();
    }

    private void init() {
        initResourcesToBuildStage1();
        initResourcesToBuildStage2();
        initResourcesToBuildStage3();
    }

    /**
     * Returns this monument's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Upgrades this monument's stage
     */
    public void buildStage() {
        stageBuilt++;
    }

    /**
     * Gives the current stage of this monument
     *
     * @return stageBuilt
     */
    public int getStageBuilt() {
        return stageBuilt;
    }

    /**
     * Gives this monument's starting resource for the player who will have it.
     *
     * @return startingResource
     */
    public ResourceEnum getStartingResource() {
        return startingResource;
    }


    /**
     * Returns the cost to build the next stage of this monument
     * @return the cost for building next stage
     */
    public List<ResourceEnum> getCostToBuildNextStage(){
        switch(stageBuilt){
            case 0:
                return getResourcesToBuildStage1();
            case 1:
                return getResourcesToBuildStage2();
            case 2:
                return getResourcesToBuildStage3();
            default:
                return null;
        }
    }

    /**
     * Returns the reward to build the next stage of this monument
     * @return the reward for building next stage
     */
    public List<ResourceEnum> getRewardForBuildingNextStage(){
        switch(stageBuilt){
            case 0:
                return getStage1Reward();
            case 1:
                return getStage2Reward();
            case 2:
                return getStage3Reward();
            default:
                return null;
        }
    }

    /**
     * Gives the resources required to build stage 1
     *
     * @return resourcesToBuildStage1
     */
    public List<ResourceEnum> getResourcesToBuildStage1() {
        return resourcesToBuildStage1;
    }

    /**
     * Gives the resources required to build stage 2
     *
     * @return resourcesToBuildStage2
     */
    public List<ResourceEnum> getResourcesToBuildStage2() {
        return resourcesToBuildStage2;
    }

    /**
     * Gives the resources required to build stage 3
     *
     * @return resourcesToBuildStage3
     */
    public List<ResourceEnum> getResourcesToBuildStage3() {
        return resourcesToBuildStage3;
    }



    /**
     * Gives the specific reward for building stage 1 of this monument
     *
     * @return resourcesToBuildStage1
     */
    public List<ResourceEnum> getStage1Reward() {
        List<ResourceEnum> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(ResourceEnum.POINT);
        }
        return list;
    }

    /**
     * Gives the specific reward for building stage 2 of this monument
     *
     * @return resourcesToBuildStage2
     */
    public abstract List<ResourceEnum> getStage2Reward();

    /**
     * Gives the specific reward for building stage 3 of this monument
     *
     * @return resourcesToBuildStage3
     */
    public List<ResourceEnum> getStage3Reward() {
        List<ResourceEnum> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(ResourceEnum.POINT);
        }
        return list;
    }

    /**
     * To be used in subclasses to initialize all stage's requirements for building
     *
     * @param resource The resource required to build a certain stage
     * @param amount   How many units required of the resource
     * @return
     */
    protected List<ResourceEnum> initializeResources(ResourceEnum resource, int amount) {
        List<ResourceEnum> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(resource);
        }
        return list;
    }
    /**
     * Initialize the resources required to build stage 1 of this monument
     */
    protected abstract void initResourcesToBuildStage1();

    /**
     * Initialize the resources required to build stage 2 of this monument
     */
    protected abstract void initResourcesToBuildStage2();

    /**
     * Initialize the resources required to build stage 3 of this monument
     */
    protected abstract void initResourcesToBuildStage3();


}