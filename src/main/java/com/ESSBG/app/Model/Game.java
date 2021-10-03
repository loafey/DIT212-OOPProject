package com.ESSBG.app.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.CardFactory;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Network.*;

public class Game {
    ConcurrentCircularList<Player> players = new ConcurrentCircularList<>(new ReentrantLock(true));
    List<Card> cardDeck;
    Trashcan trash;
    List<List<Card>> periodCards;
    List<Monument> monuments;
    int age;

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

    /**
     * Initializes cardDeck, periodCards, age, trashcan and monuments
     */

    private void init(){
        monuments = MonumentFactory.getMonuments();
        players = InitializePlayers.getInitializedPlayers(players, monuments);
        periodCards = CardFactory.getPeriodCards();
        cardDeck = periodCards.get(0);
        trash = new Trashcan();
        age = 1;
    }

    /**
     * Moves the game to the next age and changes the cardDeck to the correct cards for that specific age
     */
    private void startNextAge(){
        age++;
        cardDeck = periodCards.get(age-1);
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
            Player prev = players.getPrevious(p);
            Player next = players.getNext(p);
        }
    }


}
