package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

public interface INeighborReductionHandler extends IHandler{
    PlayerState updateState(PlayerState state);
}
