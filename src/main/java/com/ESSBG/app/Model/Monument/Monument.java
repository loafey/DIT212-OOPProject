package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public abstract class Monument implements IMonument {

    private final String name;
    private final ResourceEnum startingResource; /** The starting resource given to the player (top left corner on monument)*/
    private int stageBuilt = 0; /** How many stages that have been built yet on the monument*/
    protected List<ResourceEnum> resourcesToBuildStage1;
    protected List<ResourceEnum> resourcesToBuildStage2;
    protected List<ResourceEnum> resourcesToBuildStage3;


    //private final List<IResource> monAction; //placeholder, need to find a way to generalise all actions
    //private int monUnlock;


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

    public List<ResourceEnum> getResourcesToBuildStage1() {
        return resourcesToBuildStage1;
    }

    public List<ResourceEnum> getResourcesToBuildStage2() {
        return resourcesToBuildStage2;
    }

    public List<ResourceEnum> getResourcesToBuildStage3() {
        return resourcesToBuildStage3;
    }

    // Has to be specified for each monument
    public abstract List<ResourceEnum> stage2Reward();
    protected abstract void initResourcesToBuildStage1();
    protected abstract void initResourcesToBuildStage2();
    protected abstract void initResourcesToBuildStage3();


}