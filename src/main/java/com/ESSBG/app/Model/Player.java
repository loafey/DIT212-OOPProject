package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private List<Card> cardList;
    private int warPoints;
    private int peacePoints;
    private final int startingAmountOfMoney = 3;
    private String name;
    private final Monument monument;

    private Map<String,Integer> resources = new HashMap<>();
    //private List<List<? extends IResource>> resources;



    public Player (String name, Monument monument){
        this.name = name;
        this.monument = monument;
        cardList = new ArrayList<>();


        resources.put("Wood", 0);
        resources.put("Ore", 0);
        resources.put("Clay", 0);
        resources.put("Stone", 0);
        resources.put("Glass", 0);
        resources.put("Papyrus", 0);
        resources.put("Textiles", 0);
        resources.put("Money", startingAmountOfMoney);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getPeacePoints() {
        return peacePoints;
    }

    public void setPeacePoints(int peacePoints) {
        this.peacePoints = peacePoints;
    }

    public int getWarPoints() {
        return warPoints;
    }

    public void setWarPoints(int warPoints) {
        this.warPoints = warPoints;
    }

    /**
     * Add a card to the hand
     */

    public boolean addCard(Card card){
        // Kolla om det finns tillräckligt med resurser
        // Om inte, returna false

        // Om tillräckligt med resurser, lägg till kortet på hand
        // Uppdatera ev resurser som kortet medför
        // Hur ska vi modellera att vi redan använt en resurs på ett kort????
        return false;
    }

}