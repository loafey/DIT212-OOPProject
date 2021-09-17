package com.ESSBG.app.Model.Actions;

import com.ESSBG.app.Model.Actions.IGameAction;

public class WarAction implements IGameAction {

    int amount;

    public WarAction(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public Boolean isEndGameAction() {
        return false;
    }
}
