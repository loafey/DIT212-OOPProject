package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;

import java.util.ArrayList;
import java.util.List;

public class InitializePlayers {
    List<Monument> monuments;
    List<Player> players;

    private InitializePlayers(List<Player> players, List<Monument> monuments){
        this.players = players;
        this.monuments = monuments;
        setMonumentsToPlayers();
    }

    private void setMonumentsToPlayers(){
        for (int i=0; i<players.size(); i++){
            Player p = players.get(i);
        }
    }

    public static List<Player> getInitializedPlayers(List<Player> players, List<Monument> monuments){
        InitializePlayers initializePlayers = new InitializePlayers(players, monuments);
        return initializePlayers.players;
    }
}
