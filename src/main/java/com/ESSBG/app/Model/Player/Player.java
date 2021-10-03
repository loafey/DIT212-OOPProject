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
    private Player leftNeighbor;
    private Player rightNeighbor;


    public Player(int id, PlayerState state, Player leftNeighbor, Player rightNeighbor) {
        this.id = id;
        this.name = String.valueOf(id);
        this.state = state;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.eitherActionHandlers = new ArrayList<>();
        this.neighborReductionHandlers = new ArrayList<>();
        this.resourceActionHandlers = new ArrayList<>();

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Player p = (Player) o;
        return p.getId() == this.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerState getState() {
        return new PlayerState(state);
    }

    public void setState(PlayerState state) {
        this.state = new PlayerState(state);
    }

    public Player getLeftNeighbor() {
        return leftNeighbor;
    }

    public Player getRightNeighbor() {
        return rightNeighbor;
    }

    public void setLeftNeighbor(Player p) {
        this.leftNeighbor = p;
    }

    public void setRightNeighbor(Player p) {
        this.rightNeighbor = p;
    }

    public void setMonument(Monument monument) {
        this.monument = monument;
    }

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
     * Initialize the starting amount of coins and the play'ers monument's starting resource
     */
    public void initResources(){
        List<ResourceEnum> list = state.getGuaranteedResources();

        int k=0;
        while (k<3) {
            list.add(ResourceEnum.COIN);
            k++;
        }

        list.add(monument.getStartingResource());
        this.getState().setGuaranteedResources(list);
    }
}