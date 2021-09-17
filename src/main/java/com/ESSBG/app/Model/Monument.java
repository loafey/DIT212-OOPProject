package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Actions.IGameAction;

import java.util.ArrayList;
import java.util.List;

public class Monument {

    private final String name;
    private final IGameAction resource;
    private final List<IGameAction> monAction;
    private int monUnlock;


    public Monument(String name, IGameAction resource, List<IGameAction> monAction) {
        this.name = name;
        this.resource = resource;
        this.monAction = monAction;
        monUnlock = 0;
    }


    public String getName() {
        return name;
    }

    public IGameAction getResource() {
        return resource;
    }

    // shallow copy
    public List<IGameAction> getMonAction() {
        return new ArrayList<>(monAction);
    }

    public int getMonUnlock() {
        return monUnlock;
    }
}