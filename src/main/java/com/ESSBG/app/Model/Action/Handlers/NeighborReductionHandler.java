package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.INeighborAction;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;
/**
 * @author Sebastian Selander
 *
 * A handler used to update a PlayerState. The action is specified when creating the handler.
 * 
 */
public class NeighborReductionHandler implements INeighborHandler {

    private INeighborAction action;

    /**
     * Constructor for NeighborReductionHandler
     * @param action
     * @return NeighborReductionHandler
     */
    public NeighborReductionHandler(INeighborAction action) {
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
        List<ResourceEnum> neighborReductions = updatedState.getNeighborReductions();
        neighborReductions.addAll(action.getList());
        updatedState.setNeighborReductions(neighborReductions);
        return updatedState;
    }
}
