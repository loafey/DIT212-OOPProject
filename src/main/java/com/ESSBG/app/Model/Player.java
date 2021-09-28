package com.ESSBG.app.Model;

public class Player {
    private final int id;
    private final String name;
    private PlayerState state;


    public Player(int id, PlayerState state){// Monument monument) {
        this.id = id;
        this.name = String.valueOf(id);
        this.state = state;

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