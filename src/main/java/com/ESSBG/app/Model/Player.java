package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> playedCards;
    private List<List<? extends IResource>> resources;
    private int coinCounter;
    private int warPoints;
    private String name;
    private final Monument monument;



    public Player (String name, Monument monument, int startingAmountOfCoins){
        this.name = name;
        this.monument = monument;
        playedCards = new ArrayList<>();
        this.coinCounter =  startingAmountOfCoins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public int getCoinCounter() {
        return coinCounter;
    }

    public void setCoinCounter(int coinCounter) {
        this.coinCounter = coinCounter;
    }

    public int getWarPoints() {
        return warPoints;
    }

    public void setWarPoints(int warPoints) {
        this.warPoints = warPoints;
    }

}
