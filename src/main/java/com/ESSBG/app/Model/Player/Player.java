package com.ESSBG.app.Model.Player;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.IHandler;

public class Player {
    private final int id;
    private final String name;
    private List<IHandler> handlers;
    private PlayerState state;
    private Player leftNeighbor;
    private Player rightNeighbor;


    public Player(int id, PlayerState state, Player leftNeighbor, Player rightNeighbor){// Monument monument) {
        this.id = id;
        this.name = String.valueOf(id);
        this.state = state;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.handlers = new ArrayList<>();

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