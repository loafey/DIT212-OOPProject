package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;


public class EitherActionHandler {

    //updates the state with the new either resources added
    public static PlayerState updateState(PlayerState state, IEitherAction either) {
        PlayerState updatedState = new PlayerState(state);
        List<ArrayList<ResourceEnum>> initial = state.getEitherResources();
        List<ResourceEnum> eithers = either.getEithers();
        updatedState.setEitherResources(addEithers(initial, eithers));
        return updatedState;
    }

    //adds the list of resources to the 2d list of resources
    private static List<ArrayList<ResourceEnum>> addEithers(List<ArrayList<ResourceEnum>> initial, List<ResourceEnum> additional){
        ArrayList<ArrayList<ResourceEnum>> updatedEithers = new ArrayList<>();
        for (ArrayList<ResourceEnum> i : initial){
            updatedEithers.add(i);
        }
        updatedEithers.add(new ArrayList<>(additional));
        return updatedEithers;
    }
    
}
