package com.ESSBG.app.Model;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import com.ESSBG.app.Model.Action.Handlers.EitherHandler;
import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.Action.Handlers.NeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.ResourceHandler;
import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.CardFactory;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import com.ESSBG.app.Network.*;

public class Game {
    // TODO test
    public ConcurrentCircularList<Player> players = new ConcurrentCircularList<>(new ReentrantLock(true));
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int handSize = 7;

    // TODO NAME
    public void not_finished_run() {
        // The ordering starting server or not, preferably initialize the world first.

        // Start Lobby, wait for people to join.
        IServer server = new Server();
        ConcurrentHashMap<Integer, Player> joinedUsers = new ConcurrentHashMap<>();
        ConcurrentHashMap<Integer, Boolean> confirmedStart = new ConcurrentHashMap<>();
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Start messageListener
        (new Thread(new GameNetMessageListener(server, joinedUsers, players, confirmedStart))).start();

        // while(true) {
        // try {
        // int sx = 3;
        // } catch (InterruptedException dont_care) {
        // }
        // }
    }

    private void pickCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        Card c = currentPeriodCards.get(playerIndex).remove(cardIndex);

        if (c instanceof EitherResourceCard) {
            IEitherHandler a = new EitherHandler(((EitherResourceCard)c).getAction());
            PlayerState pState = a.updateState(p.getState());
            p.setState(pState);
        } else if (c instanceof NeighborReductionCard) {
            INeighborReductionHandler a = new NeighborReductionHandler(((NeighborReductionCard)c).getAction());
            PlayerState pState = a.updateState(p.getState());
            p.setState(pState);
        } else if (c instanceof ResourceActionCard) {
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
