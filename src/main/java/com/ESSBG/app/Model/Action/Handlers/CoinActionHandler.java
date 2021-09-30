package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Action.ICoinAction;
import com.ESSBG.app.Model.Player.PlayerState;

public class CoinActionHandler implements IHandler{

    ICoinAction action;

    public CoinActionHandler(ICoinAction action){
        this.action = action;
    }

    @Override
    public PlayerState updateState(PlayerState state) {
        PlayerState updatedState = new PlayerState(state);
        updatedState.setCoins(action.getCoins());
        return updatedState;
    }
    
}
