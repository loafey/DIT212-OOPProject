package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private List<AbstractCard> cardList;
    private Map<String, Integer> resources;
    private int warPoints;
    private int peacePoints;
    private final int startingAmountOfMoney = 3;
    private String name;
    private final Monument monument;
    private int warTokens;
    private Player leftPlayer;
    private Player rightPlayer;


    public Player (String name, Monument monument){
        this.name = name;
        this.monument = monument;
        cardList = new ArrayList<>();

        resources = new HashMap<>();
        resources.put("Wood", 0);
        resources.put("Ore", 0);
        resources.put("Clay", 0);
        resources.put("Stone", 0);
        resources.put("Glass", 0);
        resources.put("Papyrus", 0);
        resources.put("Textiles", 0);
        resources.put("Money", startingAmountOfMoney);

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

    public List<AbstractCard> getCardList() {
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
     * Add a card to the hand if the player has enough resources.
     * If the player has enough resources, update their resources and cards on hand
     * Otherwise return false
     */
    public boolean addCard(AbstractCard card){
        Map<String, Integer> balance = card.getCost();

        // Kolla om det finns tillräckligt med resurser
        // Om inte, returna false
        if (!hasSufficientResources(balance)){
            return false;
        }
        else {

            // Om tillräckligt med resurser, lägg till kortet på hand
            // Uppdatera ev resurser som kortet medför
            makeTrade(balance);
            cardList.add(card);
            return true;
        }
    }

    /** If the player has enough resources, exchange resources
     * @param balance
     */
    private void makeTrade(Map<String, Integer> balance){
        for (String key : balance.keySet()) {
            Integer valueCard = balance.get(key);
            Integer valueResource = resources.get(key);
            resources.put(key, valueCard + valueResource);
        }
    }

    private boolean hasSufficientResources(Map<String, Integer> balance){
        for (String key : balance.keySet()) {
            Integer valueCard = balance.get(key);

            // If the value for a resource is negative, it requires resources from the player
            if (valueCard < 0){
                Integer valuePlayer = resources.get(key);

                // If the player has insufficient resources, the card cannot be added to the player's hand
                if (valueCard + valuePlayer < 0){
                    return false;
                }
            }
        }
        return true;
    }

    private void useResource(String resource){
        int newAmount = resources.get(resource) - 1;
        resources.put(resource,newAmount);
    }

    private void addResource(String resource){
        int newAmount = resources.get(resource) + 1;
        resources.put(resource,newAmount);
    }

    public void addWarToken (int value){
        warPoints+=value;
    }

}