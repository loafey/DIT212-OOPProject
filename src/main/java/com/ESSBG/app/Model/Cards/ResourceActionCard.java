package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceActionCard extends Card{

    private IResourceAction action;

    /**
     * Constructs a ResourceActionCard
     * @param name
     * @param cost
     * @param color
     * @param action
     */
    public ResourceActionCard(String name, List<ResourceEnum> cost, ColorEnum color, IResourceAction action) {
        super(name, cost, color);
        
    }
    
    /**
     * Getter for IResourceAction. IResourceAction represents an action that returns a guaranteed list of resources
     * @return IResourceAction
     */
    public IResourceAction getAction(){
        return action;
    }

}
