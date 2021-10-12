package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.IAction;
import com.ESSBG.app.Model.Player.PlayerState;

public interface IHandler {
    PlayerState updateState(PlayerState state);
    IAction getAction();
}
