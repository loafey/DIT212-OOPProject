package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

/**
 * @author Sebastian Selander
 *
 * A handler used to update a PlayerState. The action is specified when creating the handler.
 * 
 */
public class ResourceHandler implements IResourceHandler{

    private IResourceAction action;

    /**
     * Constructor for a ResourceHandler
     * @param action
     * @return ResourceHandler
     */
    public ResourceHandler(IResourceAction action) {
        this.action = action;
    }

    /**
     * Update the given state using the Action. The outcome depends on how the Action is constructed.
     * @param state
     * @return PlayerState
     */
    public PlayerState updateState(PlayerState state){
        List<ResourceEnum> playerResources = state.getGuaranteedResources();
        List<ResourceEnum> additionalResources = action.getList();
        PlayerState updatedState = new PlayerState(state);
        updatedState.setGuaranteedResources(addResources(playerResources, additionalResources));
        return updatedState;
    }

    //combine two lists but in an immutable manner
    private List<ResourceEnum> addResources(List<ResourceEnum> initResources, List<ResourceEnum> additionalResources){
        List<ResourceEnum> updatedResources = new ArrayList<>(initResources);
        updatedResources.addAll(additionalResources);
        return updatedResources;
    }
}
