package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.Handlers.EitherHandler;
import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.Action.Handlers.NeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.ResourceHandler;
import com.ESSBG.app.Model.Action.IAction;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.*;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;

public class Game {
    // TODO change to private
    public CircularList<Player> players = new CircularList<>();
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int handSize = 7;

    /**
     * Puts a player's card into the trash can
     * @param playerIndex
     * @param cardIndex
     */
    private void trashCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        trash.addCard(currentPeriodCards.get(playerIndex).remove(cardIndex));
        PlayerState pState = p.getState();
        pState.addCoins(2);
        p.setState(pState);
    }

    /**
     * Upgrades a player's monument to the next stage, removes a card from their hand and upgrades their playerState,
     * if the player has efficient resources to build the next stage of their monument.
     *
     * @param playerIndex
     * @param cardIndex
     */
    private boolean upgradeMonument(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        PlayerState pState = p.getState();
        Monument m = p.getMonument();
        List<ResourceEnum> cost = m.getCostToBuildNextStage();

        if (pState.canAfford(cost)) {
            currentPeriodCards.get(playerIndex).remove(cardIndex);

            List<ResourceEnum> reward = m.getRewardForBuildingNextStage();
            if (m.getName().equals("Alexandria") || m.getName().equals("Babylon")) {
                EitherResourceAction action = new EitherResourceAction(reward);
                EitherHandler handler = new EitherHandler(action);
                handler.updateState(pState);
            } else {
                ResourceAction resourceAction = new ResourceAction(reward);
                ResourceHandler handler = new ResourceHandler(resourceAction);
                handler.updateState(pState);
            }

            m.buildStage();
            return true;
        }
        return false;
    }

    /**
     * Assuming the player has efficient resources to build the structure of card,
     * add the resources given by that card to the player's list of resources
     * @param playerIndex
     * @param cardIndex
     */

    private boolean pickCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        Card c = currentPeriodCards.get(playerIndex).remove(cardIndex);
        CardTypeEnum type = c.getCardTypeEnum();

        if (p.getState().canAfford(c.getCost())) {
            if (type == CardTypeEnum.EITHERRESOURCE) {
                IEitherHandler a = new EitherHandler(((EitherResourceCard) c).getAction());
                PlayerState pState = a.updateState(p.getState());
                pState.addEitherCard((EitherResourceCard) c);
                p.setState(pState);
            } else if (type == CardTypeEnum.NEIGHBORREDUCTION) {
                INeighborReductionHandler a = new NeighborReductionHandler(((NeighborReductionCard) c).getAction());
                PlayerState pState = a.updateState(p.getState());
                pState.addReductionCard((NeighborReductionCard) c);
                p.setState(pState);
            } else if (type == CardTypeEnum.RESOURCEACTION) {
                IResourceHandler a = new ResourceHandler(((ResourceActionCard) c).getAction());
                PlayerState pState = a.updateState(p.getState());
                pState.addResourceCard((ResourceActionCard) c);
                p.setState(pState);
            }
            return true;
        }
        return false;
    }

    /**
     * Initializes cardDeck, periodCards, age, trashcan and monuments
     */
    private void init() {
        monuments = MonumentFactory.getMonuments();
        players = InitializePlayers.getInitializedPlayers(players, monuments);
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
        trash = new Trashcan();
    }

    /**
     * Moves the game to the next age and changes the cardDeck to the correct cards for that specific age
     */
    private void startNextAge() {
        giveWarTokens(age);
        age++;
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
    }

    /**
     * Gives a player war tokens according to the predefined rules in RAD
     * @param age
     */
    private void giveWarTokens(int age) {
        // Calculate the winning points during each age
        int winPoints = (age * 2) - 1;

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            Player next = players.getNext(p);

            int pWarPoints = p.getState().getWarTokens();
            int nextWarPoints = next.getState().getWarTokens();

            // TODO make immutable
            if (pWarPoints > nextWarPoints) {
                p.getState().addWinPoints(winPoints);
                next.getState().addLosePoints(winPoints);
            } else if (pWarPoints < nextWarPoints) {
                next.getState().addWinPoints(winPoints);
                p.getState().addLosePoints(winPoints);
            }
        }
    }


    private Player calculateWinner() {
        return players.stream().reduce(players.get(0), (p1, p2) -> p1.getPoints() > p2.getPoints() ? p1 : p2);
    }
}
