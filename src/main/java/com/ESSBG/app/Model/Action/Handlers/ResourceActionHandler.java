package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceActionHandler {
    
    public static PlayerState updateState(PlayerState state, IResourceAction res){
        List<ResourceEnum> playerResources = state.getGuaranteedResources();
        List<ResourceEnum> additionalResources = res.getResources();
        PlayerState updatedState = new PlayerState(state);
        updatedState.setGuaranteedResources(addResources(playerResources, additionalResources));
        return updatedState;
    }

    private static List<ResourceEnum> addResources(List<ResourceEnum> initResources, List<ResourceEnum> additionalResources){
        List<ResourceEnum> updatedResources = new ArrayList<>(initResources);
        updatedResources.addAll(additionalResources);
        return updatedResources;
    }
}
