package com.ESSBG.app.Model.Actions;

import java.util.List;

public class EitherAction implements IGameAction {

    List<ResourceAction> resources;
    boolean chosen;

    public EitherAction(List<ResourceAction> resources) {
        this.resources = resources;
        this.chosen = false;
    }

    public ResourceAction pickAndLock(int i) {
        chosen = true;
        return resources.get(i);
    }

    public void unlock(){
        this.chosen = false;
    }

    @Override
    public Boolean isEndGameAction() {
        return false;
    }
}
