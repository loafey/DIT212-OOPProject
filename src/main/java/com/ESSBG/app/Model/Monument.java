package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Monument {

    private final String name;
    private final GameAction resource;
    private final List<GameAction> monAction;
    private int monUnlock;


    public Monument(String name, GameAction resource, List<GameAction> monAction) {
        this.name = name;
        this.resource = resource;
        this.monAction = monAction;
        monUnlock = 0;
    }


    public String getName() {
        return name;
    }

    public GameAction getResource() {
        return resource;
    }

    // shallow copy
    public List<GameAction> getMonAction() {
        return new ArrayList<>(monAction);
    }

    public int getMonUnlock() {
        return monUnlock;
    }
}