package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

/**
 * @author Sebastian Selander
 * 
 * An interface for handlers that do some either thing
 */
public interface IEitherHandler {
    PlayerState updateState(PlayerState state);
}
