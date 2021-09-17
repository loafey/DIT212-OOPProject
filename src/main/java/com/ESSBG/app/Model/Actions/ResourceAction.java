package com.ESSBG.app.Model.Actions;

public class ResourceAction implements IGameAction{

    ResourceEnum resource;
    int amount;

    public ResourceAction(ResourceEnum resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    @Override
    public Boolean isEndGameAction() {
        return false;
    }
}
