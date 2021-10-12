package com.ESSBG.app.Model.Cards;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.Handlers.EitherHandler;
import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.ResourceHandler;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class CardFactory {
    private final List<ResourceEnum> singleCoin;
    private final List<ResourceEnum> singleWood;
    private final List<ResourceEnum> noResources = new ArrayList<>();
    private List<List<Card>> allCards = new ArrayList<>();


    private CardFactory(int age, int playerAmount, int handSize) {
        List<ResourceEnum> singleCoin = new ArrayList<>();
        singleCoin.add(ResourceEnum.COIN);
        this.singleCoin = singleCoin;

        List<ResourceEnum> singleWood = new ArrayList<>();
        singleWood.add(ResourceEnum.WOOD);
        this.singleWood = singleWood;

        List<Card> cards = new ArrayList<>();

        switch (age) {
            case 1:
                cards = generateCardsAge1();
                break;
            case 2:
                cards = generateCardsAge2();
                break;
            case 3:
                cards = generateCardsAge3();
                break;
        }

        initializeHands(cards, playerAmount, handSize);

    }


    public static List<List<Card>> generateHands(int age, int playerAmount, int handSize) {
        CardFactory cardFactory = new CardFactory(age, playerAmount, handSize);
        return cardFactory.allCards;
    }


    /**
     * Initializes all cards in the game for a specific age
     * @param playerAmount The amount of players in the game
     * @param handSize     The size of a hand
     * @return all cards
     */
    private void initializeHands(List<Card> cards, int playerAmount, int handSize) {
        for (int i = 0; i < playerAmount; i++) {
            ArrayList<Card> hand = new ArrayList<>(handSize);
            for (int j = 0; j < handSize; j++) {
                hand.add(cards.get(j));
            }
            allCards.add(hand);
        }
    }


    /**
     * Generates a list of shuffled cards for age 1. All cards will have five copies in the list.
     *
     * @return list of cards for age 1
     */
    private List<Card> generateCardsAge1() {
        List<Card> list = new ArrayList<>();

        int copies = 4;

        List<ResourceEnum> tmp1 = new ArrayList<>();
        tmp1.add(ResourceEnum.STONE);
        tmp1.add(ResourceEnum.ORE);
        list.add(new EitherResourceCard("", singleCoin, ColorEnum.BROWN, new EitherHandler(new EitherResourceAction(tmp1))));


        List<ResourceEnum> tmp2 = new ArrayList<>();
        tmp2.add(ResourceEnum.WOOD);
        tmp2.add(ResourceEnum.CLAY);
        list.add(new EitherResourceCard("", singleCoin, ColorEnum.BROWN, new EitherHandler(new EitherResourceAction(tmp2))));


        List<ResourceEnum> tmp3 = new ArrayList<>();
        tmp3.add(ResourceEnum.WAR);
        list.add(new ResourceActionCard("", singleWood, ColorEnum.RED, new ResourceHandler((new ResourceAction(tmp3)))));


        List<ResourceEnum> tmp4 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tmp4.add(ResourceEnum.COIN);
        }
        list.add(new ResourceActionCard("", noResources, ColorEnum.YELLOW, new ResourceHandler((new ResourceAction(tmp4)))));


        List<ResourceEnum> tmp5 = new ArrayList<>();
        tmp5.add(ResourceEnum.GLASS);
        tmp5.add(ResourceEnum.TEXTILE);
        tmp5.add(ResourceEnum.PAPYRUS);
        list.add(new EitherResourceCard("", noResources, ColorEnum.YELLOW, new EitherHandler(new EitherResourceAction(tmp5))));


        List<ResourceEnum> tmp6 = new ArrayList<>();
        tmp6.add(ResourceEnum.ORE);
        list.add(new ResourceActionCard("", noResources, ColorEnum.BROWN, new ResourceHandler((new ResourceAction(tmp6)))));


        List<ResourceEnum> tmp7 = new ArrayList<>();
        tmp7.add(ResourceEnum.WOOD);
        list.add(new ResourceActionCard("", noResources, ColorEnum.BROWN, new ResourceHandler((new ResourceAction(tmp7)))));


        List<ResourceEnum> tmp8 = new ArrayList<>();
        tmp8.add(ResourceEnum.STONE);
        list.add(new ResourceActionCard("", noResources, ColorEnum.BROWN, new ResourceHandler((new ResourceAction(tmp8)))));

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < copies; j++) {
                list.add(list.get(j));
            }
        }

        shuffle(list);
        return list;
    }

    private List<Card> generateCardsAge2() {
        return null;
    }

    private List<Card> generateCardsAge3(){
        return null;
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

    /**
     * Generate a list of all available resources in the game
     *
     * @return
     */
    private List<ResourceEnum> getAllBasicResources() {
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
}
