package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Monuments.Monument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Player {
    private int id;
    private List<Card> cardList;
    private List<Resource> guaranteedResources;
    private List<Resource> eitherResources;
    int coins;
    private int warPoints = 0;
    private int peacePoints = 0;
    private final int startCoin = 3;
    private String name;
    private final Monument monument;
    private int warTokens;
    private Player leftPlayer;
    private Player rightPlayer;
    private HashMap<Resource, Player> reductions;

    private PlayerState state;


    public Player(int id, Monument monument) {
        this.id = id;
        this.name = String.valueOf(id);
        this.monument = monument;
        this.cardList = new ArrayList<>();
        int coins = startCoin;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Player p = (Player) o;
        return p.getId() == this.id;
    }

    public int getId() {
        return id;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public void setLeftPlayer(Player p) {
        leftPlayer = p;
    }

    public void setRightPlayer(Player p) {
        rightPlayer = p;
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

    public HashMap<Resource, Player> getReductions() {
        return new HashMap<Resource, Player>(reductions);
    }

    public void setReductions(HashMap<Resource, Player> reductions) {
        this.reductions = reductions;
    }

    public List<Resource> getGuaranteedResources() {
        return new ArrayList<>(guaranteedResources);
    }

    public List<Resource> getEitherResources() {
        return new ArrayList<>(eitherResources);
    }

    public void setGuaranteedResources(List<Resource> guaranteedResources) {
        this.guaranteedResources = guaranteedResources;
    }


    public PlayerState getState() {
        return state;
    }

    public void setEitherResources(List<Resource> eitherResources) {
        this.eitherResources = eitherResources;
    }

    /**
     * Add a card to the hand if the player has enough resources. If the player has
     * enough resources, update their resources and cards on hand Otherwise return
     * false
     */


    public void addWarToken(int value) {
        warPoints += value;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return this.coins;
    }

    public Monument getMonument(){
        return monument;
    }

    public boolean discard (Card card){
        for (Card c : cardList){
            if (c.equals(card)){
                cardList.remove(card);
                return true;
            }
        }
        return false;
    }
}