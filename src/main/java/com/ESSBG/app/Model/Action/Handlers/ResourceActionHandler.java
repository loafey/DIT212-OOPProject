package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceActionHandler implements IHandler{

    IResourceAction action;

    public ResourceActionHandler(IResourceAction action) {
        this.action = action;
    }

    public PlayerState updateState(PlayerState state){
        List<ResourceEnum> playerResources = state.getGuaranteedResources();
        List<ResourceEnum> additionalResources = action.getResources();
        PlayerState updatedState = new PlayerState(state);
        updatedState.setGuaranteedResources(addResources(playerResources, additionalResources));
        return updatedState;
    }

    private List<ResourceEnum> addResources(List<ResourceEnum> initResources, List<ResourceEnum> additionalResources){
        List<ResourceEnum> updatedResources = new ArrayList<>(initResources);
        updatedResources.addAll(additionalResources);
        return updatedResources;
    }
}