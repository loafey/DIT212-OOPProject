package com.ESSBG.app.Model.Player;

import java.util.*;

import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.ResourceEnum;

public class Player {
    private final int id;
    private final String name;
    private Monument monument;
    private List<IEitherHandler> eitherActionHandlers;
    private List<INeighborReductionHandler> neighborReductionHandlers;
    private List<IResourceHandler> resourceActionHandlers;
    private PlayerState state;

    public int getPoints(){
        return 10;
    }

    /**
     * The constructor for a Player. Creates a player with an id and a state.
     * @param id Unique id-number for each player
     * @param state A player state that holds this player's resources and cards
     */

    public Player(int id, PlayerState state) {
        this.id = id;
        this.name = String.valueOf(id);
        this.state = state;
        this.eitherActionHandlers = new ArrayList<>();
        this.neighborReductionHandlers = new ArrayList<>();
        this.resourceActionHandlers = new ArrayList<>();
    }

    /**
     * Determines if two players are equal
     * @param o
     * @return true if same id, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Player p = (Player) o;
        return p.getId() == this.id;
    }

    /**
     * Getter for name
     * @return this player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for id
     * @return this player's id
     */
    public int getId() {
        return id;
    }


    /**
     * Getter for state
     * @return a safe copy of this player's state
     */
    public PlayerState getState() {
        return new PlayerState(state);
    }

    /**
     * Setter for state
     * @param state
     */
    public void setState(PlayerState state) {
        this.state = new PlayerState(state);
    }

    /**
     * Setter for monument
     * @param monument
     */
    public void setMonument(Monument monument) {
        this.monument = monument;
    }

    /**
     * Getter for monument
     * @return
     */
    public Monument getMonument() {
        return monument;
    }


    private void getStage1Reward() {
        List<ResourceEnum> list = this.getState().getGuaranteedResources();
        for (int i = 0; i < 3; i++) {
            list.add(ResourceEnum.POINT);
        }

        PlayerState s = this.getState();
        s.setGuaranteedResources(list);
        this.setState(s);
    }

    //TODO generalisera för olika typer av monument så vi kan ha either och neighbour också
    private void getStage2reward(){
        List<ResourceEnum> list = this.getState().getGuaranteedResources();
        List<ResourceEnum> newResources = monument.stage2Reward();

        for (ResourceEnum r : newResources){
            list.add(r);
        }

        PlayerState s = this.getState();
        s.setGuaranteedResources(list);
        this.setState(s);
    }

    private void getStage3Reward() {
        List<ResourceEnum> list = this.getState().getGuaranteedResources();
        for (int i = 0; i < 7; i++) {
            list.add(ResourceEnum.POINT);
        }

        PlayerState s = this.getState();
        s.setGuaranteedResources(list);
        this.setState(s);
    }

    //TODO se till att resurserna flyttas över till rätt ställe när de använts
    /**
     * Builds a stage of this player's monument and gives this player that certain stage's reward
     * if the player has efficient resources to do so
     * @return true if build successful, false if not
     */
    public boolean buildStageOfMonument(){

        if (this.hasEfficientResourcesToBuildNextStage() && monument.getStageBuilt() < 3){
            monument.buildStage();

            switch (monument.getStageBuilt()){
                case 1:
                    getStage1Reward();
                    break;
                case 2:
                    getStage2reward();
                    break;
                case 3:
                    getStage3Reward();
            }

            //player.discard(card); //player doesn't have discard anymore
            return true;
        }

        return false;
    }

    // TODO Kollar bara just nu igenom guaranteed resources + tar ej hänsyn till om spelaren köpt något innan, fixa

    /**
     * Determine if the player can build next stage of their Monument
     * @return true if the player has efficient resources, otherwise false
     */
    private boolean hasEfficientResourcesToBuildNextStage(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.addAll(this.getState().getGuaranteedResources());

        List<ResourceEnum> requirements = getRequirementsToBuildNextStageOfMonument();

        for (ResourceEnum r : requirements){
            int indexOfResource = tmp.indexOf(r);

            // Om indexOfResource = -1 finns inte elementet i listan
            if (indexOfResource < 0){
                return false;
            }
            else {
                tmp.remove(indexOfResource);
            }
        }

        return true;
    }

    /**
     * Gives the list of resources required to build the next stage of this player's monument
     * @return the required resources to build next stage
     */
    private List<ResourceEnum> getRequirementsToBuildNextStageOfMonument(){
        int x = monument.getStageBuilt();

        switch(x+1){
            case 1:
                return monument.getResourcesToBuildStage1();
            case 2:
                return monument.getResourcesToBuildStage2();
            case 3:
                return monument.getResourcesToBuildStage3();
            default:
                throw new IllegalStateException("Monument is in weird state");
        }
    }

    /**
     * Initialize the starting amount of coins and the player's monument's starting resource
     */
    public void initResources(){
        List<ResourceEnum> list = state.getGuaranteedResources();

        int k=0;
        while (k<3) {
            list.add(ResourceEnum.COIN);
            k++;
        }

        list.add(monument.getStartingResource());
        PlayerState newState = getState();
        newState.setGuaranteedResources(list);
        setState(newState);
    }
}