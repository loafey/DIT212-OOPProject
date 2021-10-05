package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.INeighborReductionAction;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class NeighborReductionHandler implements INeighborReductionHandler {

    private INeighborReductionAction action;

    /**
     * Constructor for a NeighborReductionHandler
     * @param action
     */
    public NeighborReductionHandler(INeighborReductionAction action) {
        this.action = action;
    }

    /**
     * Returns an updated state, which is calculated using the action.
     */
    @Override
    public PlayerState updateState(PlayerState state) {
        PlayerState updatedState = new PlayerState(state);
        List<ResourceEnum> neighborReductions = updatedState.getNeighborReductions();
        neighborReductions.addAll(action.getList());
        return updatedState;
    }
}
