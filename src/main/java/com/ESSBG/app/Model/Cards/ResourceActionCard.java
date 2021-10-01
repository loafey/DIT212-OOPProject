package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceActionCard extends Card{

    private IResourceAction action;

    public ResourceActionCard(String name, List<ResourceEnum> cost, ColorEnum color, IResourceAction action) {
        super(name, cost, color);
        
    }
    
    public IResourceAction getAction(){
        return action;
    }

}
