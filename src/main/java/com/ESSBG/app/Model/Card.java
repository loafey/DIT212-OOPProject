package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Card {

    private final Map<String, Integer> balance; // A negative number means cost, a positive means the card gives the resource
    private final ColorEnum color;
    private final GameAction action;

    public Card(Map<String, Integer> balance, ColorEnum color, GameAction action) {
        this.balance = balance;
        this.color = color;
        this.action = action;
    }

    public Map<String, Integer> getBalance() {
        return balance;
    }
}
