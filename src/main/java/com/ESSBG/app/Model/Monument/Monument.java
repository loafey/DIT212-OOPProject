package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public abstract class Monument implements IMonument {

    private final String name; /**The name of this monument */
    private final ResourceEnum startingResource; /** The starting resource given to the player (top left corner on monument)*/
    private int stageBuilt = 0; /** How many stages that have been built yet on the monument (max 3)*/
    protected List<ResourceEnum> resourcesToBuildStage1;  /** The resources required to build stage 1 of this monument */
    protected List<ResourceEnum> resourcesToBuildStage2;  /** The resources required to build stage 2 of this monument */
    protected List<ResourceEnum> resourcesToBuildStage3;  /** The resources required to build stage 3 of this monument */


    //private final List<IResource> monAction; //placeholder, need to find a way to generalise all actions
    //private int monUnlock;

    /**
     * Creates a Monument with a certain name and what starting resources it will give a player that has it.
     * @param name
     * @param startingResource
     */

    public Monument(String name, ResourceEnum startingResource) {
        this.name = name;
        this.startingResource = startingResource;
        init();
    }

    private void init(){
        initResourcesToBuildStage1();
        initResourcesToBuildStage2();
        initResourcesToBuildStage3();

        if (resourcesToBuildStage1 == null || resourcesToBuildStage2 == null || resourcesToBuildStage3 == null){
            throw new IllegalStateException("Monument " + name + " hasn't been created properly. Missing cost for building at least one state");
        }
    }


    /**
     * Returns this monument's name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Upgrades this monument's stage
     */
    public void buildStage(){
        stageBuilt++;
    }

    /**
     * Gives the current stage of this monument
     * @return stageBuilt
     */

    public int getStageBuilt() {
        return stageBuilt;
    }

    /**
     * Gives this monument's starting resource for the player who will have it.
     * @return startingResource
     */

    public ResourceEnum getStartingResource() {
        return startingResource;
    }

    /**
     * To be used in subclasses to initialize all stage's requirements for building
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

    /**
     * Gives the resources required to build stage 1
     * @return resourcesToBuildStage1
     */
    public List<ResourceEnum> getResourcesToBuildStage1() {
        return resourcesToBuildStage1;
    }

    /**
     * Gives the resources required to build stage 2
     * @return resourcesToBuildStage2
     */
    public List<ResourceEnum> getResourcesToBuildStage2() {
        return resourcesToBuildStage2;
    }

    /**
     * Gives the resources required to build stage 3
     * @return resourcesToBuildStage3
     */
    public List<ResourceEnum> getResourcesToBuildStage3() {
        return resourcesToBuildStage3;
    }

    // Has to be specified for each monument

    /**
     * Gives the specific reward for building stage 2 of this monument
     * @return resourcesToBuildStage1
     */
    public abstract List<ResourceEnum> stage2Reward();

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