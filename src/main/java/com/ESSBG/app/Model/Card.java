package com.ESSBG.app.Model;


import com.ESSBG.app.Model.Actions.IGameAction;

import java.util.Map;

public class Card {

    private final Map<String, Integer> balance;
    private final ColorEnum color;
    private final IGameAction action;

    public Card(Map<String, Integer> balance, ColorEnum color, IGameAction action) {
        this.balance = balance;
        this.color = color;
        this.action = action;
    }

    public Map<String, Integer> getBalance() {
        return balance;
    }
}
