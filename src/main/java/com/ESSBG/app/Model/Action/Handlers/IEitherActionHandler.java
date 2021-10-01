package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

public interface IEitherActionHandler {
    PlayerState updateState(PlayerState state);
}
