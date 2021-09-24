package com.ESSBG.app.Model;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import com.ESSBG.app.Network.*;
import org.json.*;

public class Game {
    ConcurrentCircularList<Player> players = new ConcurrentCircularList<>(new ReentrantLock(true));
    List<AbstractCard> cardDeck;
    Trashcan trash;
    List<List<AbstractCard>> periodCards;

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

    public void updatePlayerNeighbors() {
        for (Player player : players) {
            player.setLeftPlayer(players.getPrevious(player));
            player.setRightPlayer(players.getNext(player));
        }
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

            if (p.getWarPoints() < prev.getWarPoints()) {
                p.addWarToken(-1);
            }
            if (p.getWarPoints() > prev.getWarPoints()) {
                p.addWarToken(x);
            }
            if (p.getWarPoints() < next.getWarPoints()) {
                p.addWarToken(-1);
            }
            if (p.getWarPoints() > next.getWarPoints()) {
                p.addWarToken(x);
            }
        }
    }
}
