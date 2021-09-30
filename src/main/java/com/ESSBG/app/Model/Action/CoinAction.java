package com.ESSBG.app.Model.Action;

public class CoinAction implements ICoinAction {

    int coins;

    public CoinAction(int coins){
        this.coins = coins;
    }

    @Override
    public int getCoins() {
        return coins;
    }
}
