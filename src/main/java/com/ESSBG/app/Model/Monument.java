package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Monument {

    private final String name;
    private final ResourceCard resource;
    private final List<AbstractCard> monAction;
    private int monUnlock;


    public Monument(String name, ResourceCard resource, List<AbstractCard> monAction) {
        this.name = name;
        this.resource = resource;
        this.monAction = monAction;
        monUnlock = 0;
    }


    public String getName() {
        return name;
    }

    public ResourceCard getResource() {
        return resource;
    }

    // shallow copy
    public List<AbstractCard> getMonAction() {
        return new ArrayList<>(monAction);
    }

    public int getMonUnlock() {
        return monUnlock;
    }
}