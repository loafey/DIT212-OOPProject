package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.ResourceEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class CardFactory {

    // Only for testing purposes
    public static final int nrOfWarCards = 8;
    public static final int nrOfCoinCards = 3;
    public static final int nrOfEitherCards = 6;
    public static final int nrOfNeighborRedCards = 6;
    public static final int nrOfGreenCards = 6;
    public static final int nrOfBasicResCards = 8;
    public static final int nrOfBasicLuxuryResCards = 6;
    public static final int nrOfVicPointsCards = 6;

    private static final List<ResourceEnum> noCost = new ArrayList<>();

    private CardFactory() {
    }

    public static List<List<Card>> generateHands(int age, int playerAmount, int handSize) {
        CardFactory cardFactory = new CardFactory();
        return cardFactory.getPeriodCards(age, playerAmount, handSize);
    }

    /**
     * Initializes all cards in the game for a specific age
     *
     * @param age          The current age (1-3)
     * @param playerAmount The amount of players in the game
     * @param handSize     The size of a hand
     * @return all cards
     */

    private List<List<Card>> getPeriodCards(int age, int playerAmount, int handSize) {
        List<List<Card>> playerHands = new ArrayList<>();
        List<Card> allCards = generateCards(age);
        for (int i = 0; i < playerAmount; i++) {
            List<Card> tmp = new ArrayList<>();
            for (int j = 0; j < handSize; j++) {
                Card c = allCards.get(j + i * playerAmount);
                tmp.add(c);
            }
            playerHands.add(tmp);
        }
        return playerHands;
    }

    /**
     * Generates a shuffled list of 49 cards for a certain age
     *
     * @param age
     * @return the shuffled list with 49 cards
     */
    private List<Card> generateCards(int age) {
        List<Card> cards = new ArrayList<>();
        List<ResourceEnum> basicResources = getAllBasicResources();
        int nrOfResourcesOnCard = age;
        int nrOfWarPointsOnCard = age;
        int nrOfCoinsOnCard = 5;
        List<ResourceEnum> dependingOnAgeCost;
        List<ResourceEnum> greenCardsCost;


        if (age > 1){
            dependingOnAgeCost = getRandomMediumCost(age+1);
        }
        else{
            dependingOnAgeCost = new ArrayList<>(noCost);
        }

        if (age == 1){
            greenCardsCost = getRandomMediumCost(1);
        }
        else {
            greenCardsCost = getRandomMediumCost(age + 1);
        }


        // Basic resources, two of each kind (8 cards totally)
        for (ResourceEnum r : getAllBasicResources()) {
            cards.add(new ResourceActionCard("Resource", getListOfResources(age-1, ResourceEnum.COIN), ColorEnum.BROWN, new ResourceAction(getListOfResources(nrOfResourcesOnCard, r))));
            cards.add(new ResourceActionCard("Resource", getListOfResources(age-1, ResourceEnum.COIN), ColorEnum.BROWN, new ResourceAction(getListOfResources(nrOfResourcesOnCard, r))));
        }

        // Luxury resources, two of each kind (6 cards totally)
        for (ResourceEnum r : getAllLuxuryResources()) {
            cards.add(new ResourceActionCard("Resource", new ArrayList<>(noCost), ColorEnum.GRAY, new ResourceAction(getListOfResources(nrOfResourcesOnCard, r))));
            cards.add(new ResourceActionCard("Resource", new ArrayList<>(noCost), ColorEnum.GRAY, new ResourceAction(getListOfResources(nrOfResourcesOnCard, r))));
        }

        // War
        for (int i = 0; i < nrOfWarCards; i++) {
            cards.add(new ResourceActionCard("War wagon", dependingOnAgeCost, ColorEnum.RED, new ResourceAction(getListOfResources(nrOfWarPointsOnCard, ResourceEnum.WAR))));
        }

        // Coins
        for (int i = 0; i < nrOfCoinCards; i++) {
            cards.add(new ResourceActionCard("Treasure", new ArrayList<>(noCost), ColorEnum.YELLOW, new ResourceAction(getListOfResources(nrOfCoinsOnCard, ResourceEnum.COIN))));
        }

        // Victory points
        for (int i = 0; i < 3; i++) {
            cards.add(new ResourceActionCard("Theater", dependingOnAgeCost, ColorEnum.BLUE, new ResourceAction(getListOfResources(age + 1, ResourceEnum.POINT))));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new ResourceActionCard("Baths", dependingOnAgeCost, ColorEnum.BLUE, new ResourceAction(getListOfResources(age + 2, ResourceEnum.POINT))));
        }

        cards.add(new ResourceActionCard("Pawn shop", getListOfResources(1, ResourceEnum.STONE), ColorEnum.BLUE, new ResourceAction(getListOfResources(age + 1, ResourceEnum.POINT))));


        // Either resources
        cards.add(new EitherResourceCard("Timber yard", getListOfResources(age-1, ResourceEnum.COIN), ColorEnum.BROWN, new EitherResourceAction(getListOfEitherResources(ResourceEnum.WOOD, ResourceEnum.STONE))));
        cards.add(new EitherResourceCard("Clay pit", getListOfResources(age-1, ResourceEnum.COIN), ColorEnum.BROWN, new EitherResourceAction(getListOfEitherResources(ResourceEnum.CLAY, ResourceEnum.ORE))));
        cards.add(new EitherResourceCard("Mine", getListOfResources(age-1, ResourceEnum.COIN), ColorEnum.BROWN, new EitherResourceAction(getListOfEitherResources(ResourceEnum.STONE, ResourceEnum.ORE))));

        cards.add(new EitherResourceCard("Caravansery", getListOfResources(2, ResourceEnum.WOOD), ColorEnum.YELLOW, new EitherResourceAction(getAllBasicResources())));
        cards.add(new EitherResourceCard("Caravansery", getListOfResources(2, ResourceEnum.WOOD), ColorEnum.YELLOW, new EitherResourceAction(getAllBasicResources())));
        cards.add(new EitherResourceCard("Forum", getListOfResources(2, ResourceEnum.CLAY), ColorEnum.YELLOW, new EitherResourceAction(getAllLuxuryResources())));


        // Neighbor red resources
        for (int i = 0; i < nrOfNeighborRedCards / 2; i++) {
            cards.add(new NeighborReductionCard("Trading post", new ArrayList<>(noCost), ColorEnum.YELLOW, new NeighborReductionAction(getAllBasicResources())));
            cards.add(new NeighborReductionCard("Market place", new ArrayList<>(noCost), ColorEnum.YELLOW, new NeighborReductionAction(getAllLuxuryResources())));
        }

        // Lib, lab, disp
        for (int i = 0; i < nrOfGreenCards / 3; i++) {
            cards.add(new ResourceActionCard("Library", greenCardsCost, ColorEnum.GREEN, new ResourceAction(getListOfResources(1, ResourceEnum.Library))));
            cards.add(new ResourceActionCard("Laboratory", greenCardsCost, ColorEnum.GREEN, new ResourceAction(getListOfResources(1, ResourceEnum.Laboratory))));
            cards.add(new ResourceActionCard("Dispensary", greenCardsCost, ColorEnum.GREEN, new ResourceAction(getListOfResources(1, ResourceEnum.Dispensary))));
        }

        Random r = new Random(getSeed());
        Collections.shuffle(cards,r);
        return cards;
    }

    /**
     * Generates an int based on today's date to be used for generating random numbers
     * @return
     */
    private int getSeed() {
        LocalDate d = java.time.LocalDate.now();
        int seed = 0;
        seed += d.getYear();
        seed += d.getMonthValue();
        seed += d.getDayOfMonth();
        return seed;
    }



    /**
     * Get a list of specific resources
     *
     * @param r1 one resource
     * @param r2 another resource
     * @return list of r1 and r2
     */

    private List<ResourceEnum> getListOfEitherResources(ResourceEnum r1, ResourceEnum r2) {
        List<ResourceEnum> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);
        return list;
    }


    /**
     * Gives the correct color for each resource type
     *
     * @param r resource
     * @return color for the resource
     */
    private ColorEnum getColor(ResourceEnum r) {
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

    private List<ResourceEnum> getRandomMediumCost(int items){
        Random r = new Random();
        List<ResourceEnum> list = getAllBasicResources();
        List<ResourceEnum> tmp = new ArrayList<>();

        for (int i=0; i<items; i++){
            tmp.add(list.get(r.nextInt(4)));
        }

        return tmp;
    }

    /**
     * Generate a list of all available basic resources in the game
     *
     * @return
     */
    private List<ResourceEnum> getAllBasicResources() {
        List<ResourceEnum> list = new ArrayList<>();
        list.add(ResourceEnum.WOOD);
        list.add(ResourceEnum.CLAY);
        list.add(ResourceEnum.GLASS);
        list.add(ResourceEnum.ORE);
        return list;
    }

    /**
     * Generate a list of all available luxury resources in the game
     *
     * @return
     */
    private List<ResourceEnum> getAllLuxuryResources() {
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
    private List<ResourceEnum> getListOfResources(int amount, ResourceEnum resource) {
        List<ResourceEnum> resources = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            resources.add(resource);
        }

        return resources;
    }

    /**
     * Get the total number of cards intended before the cards are handed out to the players
     * @return total amount of cards
     */
    public static int getNrOfTotalCards() {
        return nrOfWarCards +
                nrOfCoinCards +
                nrOfEitherCards +
                nrOfGreenCards +
                nrOfNeighborRedCards +
                nrOfBasicLuxuryResCards +
                nrOfBasicResCards +
                nrOfVicPointsCards;
    }


}
