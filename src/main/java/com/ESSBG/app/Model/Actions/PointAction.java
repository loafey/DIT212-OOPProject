package com.ESSBG.app.Model.Actions;

import com.ESSBG.app.Model.Actions.IGameAction;

public class PointAction implements IGameAction {

    int points;

    public PointAction(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public Boolean isEndGameAction() {
        return true;
    }
}
