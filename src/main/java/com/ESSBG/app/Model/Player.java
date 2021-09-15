package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private List<Card> playedCards;
    //private List<List<? extends IResource>> resources;
    private int coinCounter;
    private int warPoints;
    private String name;
    private final Monument monument;
    private Map<String,Integer> resources = new HashMap<>();



    public Player (String name, Monument monument, int startingAmountOfCoins){
        this.name = name;
        this.monument = monument;
        playedCards = new ArrayList<>();
        this.coinCounter =  startingAmountOfCoins;

        resources.put("Wood", 0);
        resources.put("Ore", 0);
        resources.put("Clay", 0);
        resources.put("Stone", 0);
        resources.put("Glass", 0);
        resources.put("Papyrus", 0);
        resources.put("Textiles", 0);

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

    public void addResource(String resource){
        resources.put(resource, resources.get(resource) + 1);
    }

}