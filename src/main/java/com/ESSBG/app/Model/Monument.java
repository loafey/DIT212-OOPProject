package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IAction;

import java.util.ArrayList;
import java.util.List;

public class Monument implements IBuyable {

    private final String name;
    private final List<Resource> generate;
    private final List<IAction> monAction;
    private int monUnlock;


    public Monument(String name, List<Resource> generate, List<IAction> monAction) {
        this.name = name;
        this.generate = generate;
        this.monAction = monAction;
        monUnlock = 0;
    }


    public String getName() {
        return name;
    }

    public List<Resource> getGenerate() {
        return generate;
    }

    // shallow copy
    public List<IAction> getMonAction() {
        return new ArrayList<>(monAction);
    }

    public int getMonUnlock() {
        return monUnlock;
    }

    @Override
    public List<Resource> getCost() {
        return null;
    }
}