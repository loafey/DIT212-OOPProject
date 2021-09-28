package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.INeighborReduction;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public class NeighborReductionHandler implements IHandler {

    INeighborReduction action;

    public NeighborReductionHandler(INeighborReduction action) {
        this.action = action;
    }

    @Override
    public PlayerState updateState(PlayerState state) {
        PlayerState updatedState = new PlayerState(state);
        List<ResourceEnum> leftReductions = updatedState.getLeftPlayerReductions();
        List<ResourceEnum> rightReductions = updatedState.getRightPlayerReductions();
        leftReductions.addAll(state.getLeftPlayerReductions()); //concat the lists
        rightReductions.addAll(state.getRightPlayerReductions());
        return updatedState;
    }
}
