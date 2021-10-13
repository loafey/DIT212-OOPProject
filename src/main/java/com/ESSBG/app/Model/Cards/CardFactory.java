package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardFactory {
    private final List<ResourceEnum> noCost = new ArrayList<>();

    private CardFactory(){
    }

    public static List<List<Card>> getPeriodCards() {
        return null;
    }

    /**
     * Initializes all cards in the game for a specific age
     *
     * @param age          The current age (1-3)
     * @param playerAmount The amount of players in the game
     * @param handSize     The size of a hand
     * @return all cards
     */
    public static List<List<Card>> generateHands(int age, int playerAmount, int handSize) {
        ArrayList<List<Card>> allCards = new ArrayList<>(playerAmount);
        for (int i = 0; i < playerAmount; i++) {
            ArrayList<Card> hand = new ArrayList<>(handSize);
            for (int j = 0; j < handSize; j++) {
                hand.add(generateCards(age).get(j));
            }
            allCards.add(hand);
        }
        return allCards;
    }


    public static List<Card> generateCards(int age) {
        List<Card> cards = new ArrayList<>();
        List<ResourceEnum> basicResources = getAllBasicResources();
        int nrOfResourceCopies = 2;
        int nrOfResources = age;
        int nrOfWarPointsOnCard = age;
        int nrOfWarCards = 2 * age + 4;
        int nrOfCoinsOnCard = 3 + 2 * age;
        int nrOfCoinCards = age * 3;

        // Basic resources
        for (ResourceEnum r : getAllBasicResources()) {
            cards.add(new ResourceActionCard("Resource", noCost, ColorEnum.BROWN, new ResourceAction(getListOfResources(nrOfResources, r))));

        }
        // Luxury resources
        for (ResourceEnum r : getAllLuxuryResources()) {
            cards.add(new ResourceActionCard("Resource", noCost, ColorEnum.GRAY, new ResourceAction(getListOfResources(nrOfResources, r))));

        }

        // War
        for (int i = 0; i < nrOfWarCards; i++) {
            cards.add(new ResourceActionCard("War wagon", noCost, ColorEnum.RED, new ResourceAction(getListOfResources(nrOfWarPointsOnCard, ResourceEnum.WAR))));
        }

        // Coins
        for (int i = 0; i < nrOfCoinCards; i++) {
            cards.add(new ResourceActionCard("Treasure", noCost, ColorEnum.YELLOW, new ResourceAction(getListOfResources(nrOfCoinsOnCard, ResourceEnum.COIN))));
        }


        // Victory points
        // Either resources
        // Neighbor red resources
        // Lib, lab, disp

        return cards;
    }


    /**
     * Gives the correct color for each resource type
     *
     * @param r resource
     * @return color for the resource
     */
    private static ColorEnum getColor(ResourceEnum r) {
        if (r.equals(ResourceEnum.WAR)) {
            return ColorEnum.RED;
        } else if (r.equals(ResourceEnum.POINT)) {
            return ColorEnum.BLUE;
        } else if (r.equals(ResourceEnum.COIN)) {
            return ColorEnum.YELLOW;
        } else if (r.equals(ResourceEnum.Laboratory) || r.equals(ResourceEnum.Dispensary) || r.equals(ResourceEnum.Library)) {
            return ColorEnum.GREEN;
        } else {
            return ColorEnum.BROWN;
        }
    }

    /**
     * Generate a list of all available resources in the game
     *
     * @return
     */
    private static List<ResourceEnum> getAllBasicResources() {
        List<ResourceEnum> list = new ArrayList<>();
        list.add(ResourceEnum.WOOD);
        list.add(ResourceEnum.CLAY);
        list.add(ResourceEnum.GLASS);
        list.add(ResourceEnum.ORE);
        return list;
    }

    /**
     * Generate a list of all available resources in the game
     *
     * @return
     */
    private static List<ResourceEnum> getAllLuxuryResources() {
        List<ResourceEnum> list = new ArrayList<>();

        list.add(ResourceEnum.PAPYRUS);
        list.add(ResourceEnum.STONE);
        list.add(ResourceEnum.TEXTILE);
        return list;
    }

    /**
     * Get a list of resources of a certain amount
     *
     * @param amount   how many items you want to have
     * @param resource the kind of resource you want to have
     * @return
     */
    private static List<ResourceEnum> getListOfResources(int amount, ResourceEnum resource) {
        List<ResourceEnum> resources = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            resources.add(resource);
        }

        return resources;
    }
}
