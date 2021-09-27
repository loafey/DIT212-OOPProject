package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IResource;

import java.util.ArrayList;
import java.util.List;

public class Monument implements IBuyable {

    private final String name;
    private final List<Resource> generate;
    private final List<IResource> monAction; //placeholder, need to find a way to generalise all actions
    private int monUnlock;


    public Monument(String name, List<Resource> generate, List<IResource> monAction) {
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
    public List<IResource> getMonAction() {
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