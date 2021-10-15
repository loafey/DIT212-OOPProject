package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.INeighborReductionAction;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class NeighborReductionHandler implements INeighborReductionHandler {

    private INeighborReductionAction action;

    /**
     * Constructor for NeighborReductionHandler
     * @param action
     * @return NeighborReductionHandler
     */
    public NeighborReductionHandler(INeighborReductionAction action) {
        this.action = action;
    }

    /**
     * Updates the given state using the action.
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
