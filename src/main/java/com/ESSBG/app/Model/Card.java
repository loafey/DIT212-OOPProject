package com.ESSBG.app.Model;

import jdk.internal.loader.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Card {

    private final Map<String, Integer> balance;
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
