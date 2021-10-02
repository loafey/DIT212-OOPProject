package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;


public class EitherHandler implements IEitherHandler {

    IEitherAction action;

    public EitherHandler(IEitherAction action) {
        this.action = action;
    }

    /**
     * Uses the action and current state to generate an updated state.
     */
    @Override
    public PlayerState updateState(PlayerState state) {
        PlayerState updatedState = new PlayerState(state);
        List<ArrayList<ResourceEnum>> initial = state.getEitherResources();
        List<ResourceEnum> eithers = action.getList();
        updatedState.setEitherResources(addEithers(initial, eithers));
        return updatedState;
    }

    //adds the list of resources to the 2d list of resources
    private List<ArrayList<ResourceEnum>> addEithers(List<ArrayList<ResourceEnum>> initial, List<ResourceEnum> additional){
        ArrayList<ArrayList<ResourceEnum>> updatedEithers = new ArrayList<>();
        for (ArrayList<ResourceEnum> i : initial){
            updatedEithers.add(i);
        }
        updatedEithers.add(new ArrayList<>(additional));
        return updatedEithers;
    }
}
