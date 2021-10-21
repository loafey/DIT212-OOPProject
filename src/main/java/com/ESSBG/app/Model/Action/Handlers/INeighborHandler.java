package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Player.PlayerState;

/**
 * @author Sebastian Selander
 * 
 * An interface for handlers that do has an effect dependent on neighbors
 */
public interface INeighborHandler {
    PlayerState updateState(PlayerState state);
}
