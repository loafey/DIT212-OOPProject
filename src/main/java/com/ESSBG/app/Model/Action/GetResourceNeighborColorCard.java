package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.*;
import com.ESSBG.app.Model.Cards.Card;

import java.util.HashMap;
import java.util.List;

/*
This class represents the card that gives a type of resource (points usually)
 for each colored (only one color, for example yellow) card the neighbors have.
 Check assets/Cards/TradersGuild.jpg for a concrete example.
*/
public class GetResourceNeighborColorCard implements INeighborAction {

    private List<Resource> resources;
    private boolean leftNeighbor;
    private boolean rightNeighbor;
    private ColorEnum color;

    public GetResourceNeighborColorCard(List<Resource> resources, boolean leftNeighbor, boolean rightNeighbor, ColorEnum color, int amount) {
        this.resources = resources;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.color = color;
    }

    @Override
    public boolean rightNeighbor() {
        return this.rightNeighbor;
    }

    @Override
    public boolean leftNeighbor() {
        return this.leftNeighbor;
    }

    @Override
    public List<Resource> resources() {
        return resources;
    }

    private int getAmount(Player p1, Player p2) {
        int amount = 0;
        if (leftNeighbor) {
            for (Card c : p1.getCardList()) {
                if (c.getColor().equals(color)) amount++;
            }
        }
        if (rightNeighbor) {
            for (Card c : p2.getCardList()) {
                if (c.getColor().equals(color)) amount++;
            }
        }
        return amount;
    }

    public HashMap<Resource, Integer> getResources(Player p1, Player p2) {
        int amount = getAmount(p1, p2);
        HashMap<Resource, Integer> map = new HashMap<>();
        for (Resource r : resources) {
            map.put(r, amount);
        }
        return map;
    }

    @Override
    public void doAction(Player p) {

    }
}
