package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

public interface IResourceHandler extends IHandler{
    PlayerState updateState(PlayerState state);
}
