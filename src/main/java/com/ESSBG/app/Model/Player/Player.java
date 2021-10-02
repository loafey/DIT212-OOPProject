package com.ESSBG.app.Model.Player;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;

public class Player {
    private final int id;
    private final String name;
    private List<IEitherHandler> eitherActionHandlers;
    private List<INeighborReductionHandler> neighborReductionHandlers;
    private List<IResourceHandler> resourceActionHandlers;
    private PlayerState state;
    private Player leftNeighbor;
    private Player rightNeighbor;


    public Player(int id, PlayerState state, Player leftNeighbor, Player rightNeighbor){// Monument monument) {
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

    public PlayerState getState(){
        return new PlayerState(state);
    }

    public void setState(PlayerState state){
        this.state = new PlayerState(state);
    }

    public Player getLeftNeighbor(){
        return leftNeighbor;
    }

    public Player getRightNeighbor(){
        return rightNeighbor;
    }

    public void setLeftNeighbor(Player p){
        this.leftNeighbor = p;
    }

    public void setRightNeighbor(Player p){
        this.rightNeighbor = p;
    }
}