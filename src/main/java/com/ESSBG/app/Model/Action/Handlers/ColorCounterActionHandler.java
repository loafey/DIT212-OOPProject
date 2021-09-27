package com.ESSBG.app.Model.Action.Handlers;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.ColorEnum;
import com.ESSBG.app.Model.PlayerState;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.ColoredCardResource;

import java.util.List;

public class ColorCounterActionHandler {

    public PlayerState updateState(PlayerState state, ColoredCardResource ccr){
        int initialCoins = state.getCoins();
        List<ResourceEnum> totalGuaranteedResources = state.getGuaranteedResources();
        for (int i = 0; i < calculateTotalColoredCardsMatch(state, ccr) * ccr.getPointsPerCard(); i++){
            totalGuaranteedResources.add(ResourceEnum.POINT);
        }

        int totalCoins = initialCoins + calculateTotalColoredCardsMatch(state, ccr) * ccr.getCoinsPerCard();

        PlayerState updatedState = new PlayerState(state);

        updatedState.setCoins(totalCoins);
        updatedState.setGuaranteedResources(totalGuaranteedResources);

        return updatedState;
    }

    //calculate how many cards from specified neighbors and/or self match the color.
    private static int calculateTotalColoredCardsMatch(PlayerState state, ColoredCardResource ccr){
        List<Card> leftNeighborCards = state.getleftPlayerState().getPlayedCards();
        List<Card> rightNeighborCards = state.getRightPlayerState().getPlayedCards();
        List<Card> selfCards = state.getPlayedCards();

        int counter = 0;

        if (ccr.isLeftNeighbor()) counter += calculatePlayerColorMatch(leftNeighborCards, ccr.getColor());
        if (ccr.isSelf()) counter += calculatePlayerColorMatch(selfCards, ccr.getColor());
        if (ccr.isRightNeighbor()) counter += calculatePlayerColorMatch(rightNeighborCards, ccr.getColor());
        return counter;
    }

    //calculate how many cards a player has played that match the specified color
    private static int calculatePlayerColorMatch(List<Card> playedCards, ColorEnum color){
        int counter = 0;
        for (Card c : playedCards){
            if (c.getColor().equals(color)) counter++;
        }
        return counter;
    }
}
