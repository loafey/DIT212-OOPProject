package com.ESSBG.app;

import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ConcurrentCircularList;
import com.ESSBG.app.Model.Game;
import com.ESSBG.app.Model.Trashcan;
import com.ESSBG.app.Model.Cards.*;
import com.ESSBG.app.Model.Monument.*;
import com.ESSBG.app.Network.*;
import org.json.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class reduntant_GameServer {
    private IServer server;
    private Game game;
    private GameServer clientListener;

    private LinkedBlockingQueue<JSONObject> msgQueue;
    private ConcurrentHashMap<Integer, Player> joinedUsers;
    private ConcurrentHashMap<Integer, Boolean> confirmedStart;

    private ConcurrentCircularList<Player> players;
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int roundsPerAge = 6;
    private final int handSize = 7;

    protected reduntant_GameServer() {
        this.server = new Server();
        this.joinedUsers = new ConcurrentHashMap<>();
        this.confirmedStart = new ConcurrentHashMap<>();
        // this.clientListener = ;
    }

    // Checks if all users have locked in the round.
    private boolean allUsersConfirmedNextRound() {
        for (boolean b : confirmedStart.values()) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    protected void start() {
        try {
            server.runServer();
            (new Thread(new GameServer())).start();

            // Main loop.
            while(true) {
                for (int round = 0; round < roundsPerAge; round++) {
                    while(!allUsersConfirmedNextRound()) {
                        Thread.sleep(100); // Slow down this thread
                    }
                }
            }
        } catch (Exception ignore) {
        }
    }
}
