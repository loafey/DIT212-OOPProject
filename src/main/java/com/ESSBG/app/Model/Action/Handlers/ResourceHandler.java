package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.IAction;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceHandler implements IResourceHandler{

    private IResourceAction action;

    /**
     * Constructor for a ResourceHandler
     * @param action
     */
    public ResourceHandler(IResourceAction action) {
        this.action = action;
    }

    /**
     * Returns an updated state, which is caluclated using action
     * @param state
     */
    public PlayerState updateState(PlayerState state){
        List<ResourceEnum> playerResources = state.getGuaranteedResources();
        List<ResourceEnum> additionalResources = action.getList();
        PlayerState updatedState = new PlayerState(state);
        updatedState.setGuaranteedResources(addResources(playerResources, additionalResources));
        return updatedState;
    }

    private List<ResourceEnum> addResources(List<ResourceEnum> initResources, List<ResourceEnum> additionalResources){
        List<ResourceEnum> updatedResources = new ArrayList<>(initResources);
        updatedResources.addAll(additionalResources);
        return updatedResources;
    }

    @Override
    public IAction getAction() {
        return action;
    }
}
