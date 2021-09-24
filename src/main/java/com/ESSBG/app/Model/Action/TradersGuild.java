package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.*;

import java.util.List;

public class TradersGuild implements INeighboraction {

    List<Resource> resources;
    boolean leftNeighbor;
    boolean rightNeighbor;
    ColorEnum color;
    int amountPerCard;

    public TradersGuild(List<Resource> resources, boolean leftNeighbor, boolean rightNeighbor, ColorEnum color, int amount) {
        this.resources = resources;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.color = color;
        this.amountPerCard = amount;
    }

    @Override
    public boolean rightNeighbor() {
        return false;
    }

    @Override
    public boolean leftNeighbor() {
        return false;
    }

    @Override
    public List<Resource> resources() {
        return null;
    }

    public int getPoints(Player p1, Player p2) {
        int points = 0;
        if (leftNeighbor) {
            for(AbstractCard c : p1.getCardList()){
                if (c.getColor().equals(color)) points++;
            }
        }
        if (rightNeighbor){
            for (AbstractCard c : p2.getCardList()){
                if (c.getColor().equals(color)) points++;
            }
        }
        return points * amountPerCard;
    }
}
