package com.ESSBG.app.Model.Player;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.IHandler;

public class Player {
    private final int id;
    private final String name;
    private List<IHandler> handlers;
    private PlayerState state;


    public Player(int id, PlayerState state){// Monument monument) {
        this.id = id;
        this.name = String.valueOf(id);
        this.state = state;
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
}