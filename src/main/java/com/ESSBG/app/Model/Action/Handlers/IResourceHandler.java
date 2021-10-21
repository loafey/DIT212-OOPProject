package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

/**
 * @author Sebastian Selander
 * 
 * An interface for handlers that affect the resources of a state
 */
public interface IResourceHandler {
    PlayerState updateState(PlayerState state);
}
