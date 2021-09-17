package com.ESSBG.app.Model.Actions;

public class ScientificAction implements IGameAction {

    ScientificEnum type;

    public ScientificAction(ScientificEnum type) {
        this.type = type;
    }

    @Override
    public Boolean isEndGameAction() {
        return true;
    }
}
