package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardFactory {
    public static List<List<Card>> getPeriodCards(){
        return null;
    }

    /**
     * Initializes all cards in the game for a specific age
     * @param age The current age (1-3)
     * @param playerAmount The amount of players in the game
     * @param handSize The size of a hand
     * @return all cards
     */
    public List<List<Card>> generateHands(int age, int playerAmount, int handSize) {
        ArrayList<List<Card>> allCards = new ArrayList<>(playerAmount);
        for (int i = 0; i < playerAmount; i++){
            ArrayList<Card> hand = new ArrayList<>(handSize);
            for (int j = 0; j < handSize; j++){
                hand.add(generateRandomResourceCard());
            }
            allCards.add(hand);
        }
        return allCards;
    }



    /**
     * Generates a random resource card
     * @return A random card
     */
    private ResourceActionCard generateRandomResourceCard(){
        Random r = new Random();

        List<ResourceEnum> availableResources = getAllBasicResources();
        ResourceEnum resource = availableResources.get(r.nextInt(availableResources.size()));

        int boundary;

        if (resource.equals(ResourceEnum.COIN)){
            boundary = 9;
        }
        else {
            boundary = 2;
        }

        int amount = 1 + r.nextInt(boundary);


        ResourceActionCard card = new ResourceActionCard("", null, getColor(resource), new ResourceAction(getListOfResources(amount, resource)));
        return card;
    }

    /**
     * Gives the correct color for each resource type
     * @param r resource
     * @return color for the resource
     */
    private ColorEnum getColor(ResourceEnum r){
        if (r.equals(ResourceEnum.WAR)){
            return ColorEnum.RED;
        }
        else if (r.equals(ResourceEnum.POINT)){
            return ColorEnum.BLUE;
        }
        else if (r.equals(ResourceEnum.COIN)){
            return ColorEnum.YELLOW;
        }
        else if(r.equals(ResourceEnum.Laboratory) || r.equals(ResourceEnum.Dispensary) || r.equals(ResourceEnum.Library)){
            return ColorEnum.GREEN;
        }
        else {
            return ColorEnum.BROWN;
        }
    }

    /**
     * Generate a list of all available resources in the game
     * @return
     */
    private List<ResourceEnum> getAllBasicResources(){
        List<ResourceEnum> list = new ArrayList<>();
        list.add(ResourceEnum.WOOD);
        list.add(ResourceEnum.CLAY);
        list.add(ResourceEnum.GLASS);
        list.add(ResourceEnum.ORE);
        list.add(ResourceEnum.PAPYRUS);
        list.add(ResourceEnum.STONE);
        list.add(ResourceEnum.TEXTILE);
        return list;
    }

    /**
     * Get a list of resources of a certain amount
     * @param amount how many items you want to have
     * @param resource the kind of resource you want to have
     * @return
     */
    private List<ResourceEnum> getListOfResources(int amount, ResourceEnum resource){
        List<ResourceEnum> resources = new ArrayList<>();

        for (int i=0; i<amount; i++){
            resources.add(resource);
        }

        return resources;
    }
}
