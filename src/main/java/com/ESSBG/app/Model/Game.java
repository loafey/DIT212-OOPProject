package com.ESSBG.app.Model;

import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.EitherHandler;
import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.Action.Handlers.NeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.ResourceHandler;
import com.ESSBG.app.Model.Cards.*;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;

public class Game {
    // TODO change to private
    public ConcurrentCircularList<Player> players = new ConcurrentCircularList<>();
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int handSize = 7;



    private void pickCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        Card c = currentPeriodCards.get(playerIndex).remove(cardIndex);
        CardTypeEnum type = c.getCardTypeEnum();

        if (type == CardTypeEnum.EITHERRESOURCE) {
            IEitherHandler a = new EitherHandler(((EitherResourceCard)c).getAction());
            PlayerState pState = a.updateState(p.getState());
            p.setState(pState);
        } else if (type == CardTypeEnum.NEIGHBORREDUCTION) {
            INeighborReductionHandler a = new NeighborReductionHandler(((NeighborReductionCard)c).getAction());
            PlayerState pState = a.updateState(p.getState());
            p.setState(pState);
        } else if (type == CardTypeEnum.RESOURCEACTION) {
            IResourceHandler a = new ResourceHandler(((ResourceActionCard)c).getAction());
            PlayerState pState = a.updateState(p.getState());
            p.setState(pState);
        } else {
            System.out.println("what?");
        }
    }   

    /**
     * Initializes cardDeck, periodCards, age, trashcan and monuments
     */

    private void init(){
        monuments = MonumentFactory.getMonuments();
        players = InitializePlayers.getInitializedPlayers(players, monuments);
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
        trash = new Trashcan();
    }

    /**
     * Moves the game to the next age and changes the cardDeck to the correct cards for that specific age
     */
    private void startNextAge(){
        giveWarTokens(age);
        age++;
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
    }

    // Use this method to give war tokens after each age
    private void giveWarTokens(int age) {
        int x = 0;

        // Calculate the winning points during each age
        switch (age) {
            case 1:
                x = 1;
                break;
            case 2:
                x = 3;
                break;
            case 3:
                x = 5;
                break;
        }

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            Player next = players.getNext(p);
        
            int pWarPoints = p.getState().getWarTokens();
            int nextWarPoints = next.getState().getWarTokens();

            // TODO make immutable
            if (pWarPoints > nextWarPoints) {
                p.getState().addWinPoints(x);
                next.getState().addLosePoints(x);
            } else if (pWarPoints < nextWarPoints) {
                next.getState().addWinPoints(x);
                p.getState().addLosePoints(x);
            }
        }
    }
}
