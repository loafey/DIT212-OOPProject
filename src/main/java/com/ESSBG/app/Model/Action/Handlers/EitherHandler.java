package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Player.PlayerState;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;

/**
 * @author Sebastian Selander
 *
 * A handler used to update a PlayerState. The action is specified when creating the handler.
 * 
 */
public class EitherHandler implements IEitherHandler {

    private IEitherAction action;

    /**
     * Constructor for EitherHandler
     * @param action
     * @return EitherHandler
     */
    public EitherHandler(IEitherAction action) {
        this.action = action;
    }

    
    /**
     * Update the given state using the Action. The outcome depends on how the Action is constructed.
     * @param state
     * @return PlayerState
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
