package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Cards.ColorEnum;

/*
This class represents the card that gives a type of resource (points usually)
 for each colored (only one color, for example yellow) card the neighbors have.
 Check assets/Cards/TradersGuild.jpg for a concrete example.
*/
public class ColoredCardResource {

    private final int pointsPerCard;
    private final int coinsPerCard;
    private final boolean self;
    private final boolean leftNeighbor;
    private final boolean rightNeighbor;
    private final ColorEnum color;

    public ColoredCardResource(int points, int coins, boolean self, boolean leftNeighbor, boolean rightNeighbor, ColorEnum color, int amount) {
        this.pointsPerCard = points;
        this.coinsPerCard = coins;
        this.self = self;
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        this.color = color;
    }

    public int getPointsPerCard() {
        return pointsPerCard;
    }

    public int getCoinsPerCard() {
        return coinsPerCard;
    }

    public boolean isSelf() {
        return self;
    }

    public boolean isLeftNeighbor() {
        return leftNeighbor;
    }

    public boolean isRightNeighbor() {
        return rightNeighbor;
    }

    public ColorEnum getColor() {
        return color;
    }
}
