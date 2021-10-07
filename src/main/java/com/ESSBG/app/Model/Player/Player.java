package com.ESSBG.app.Model.Player;

import java.util.ArrayList;
import java.util.List;

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
    private Player leftNeighbor;    //remove, player shouldn't hold his own neighbors
    private Player rightNeighbor;

    /**
     * The constructor for a Player. Creates a player with an id, a state and initializes it's neighbors.
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
     * Getter for id
     * @return this player's id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
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
        this.getState().setGuaranteedResources(list);
    }

    //TODO generalisera för olika typer av monument så vi kan ha either och neighbour också
    private void getStage2reward(){
        List<ResourceEnum> list = this.getState().getGuaranteedResources();
        List<ResourceEnum> newResources = monument.stage2Reward();

        for (ResourceEnum r : newResources){
            list.add(r);
        }

        this.getState().setGuaranteedResources(list);
    }

    private void getStage3Reward() {
        List<ResourceEnum> list = this.getState().getGuaranteedResources();
        for (int i = 0; i < 7; i++) {
            list.add(ResourceEnum.POINT);
        }
        this.getState().setGuaranteedResources(list);
    }

    /**
     * Builds a stage of this player's monument if the player has efficient resources to do so
     * @return true if build successful, false if not
     */
    public boolean buildStageOfMonument(){
        boolean playerHasEfficientResources = false;    //TODO  Vet ej hur man ska kolla det

        if (playerHasEfficientResources && monument.getStageBuilt() < 3){
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