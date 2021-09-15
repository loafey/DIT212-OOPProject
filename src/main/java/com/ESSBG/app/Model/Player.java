package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final Monument monument;
    private List<Card> cardsOnHand;


    public Player (String name, Monument monument){
        this.name = name;
        this.monument = monument;
        cardsOnHand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardsOnHand() {
        return cardsOnHand;
    }

}
