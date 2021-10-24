package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.CircularList;
import com.ESSBG.app.Model.Monument.Monument;

import java.util.List;

public class InitializePlayers {
    private List<Monument> monuments;
    private CircularList<Player> players;

    private InitializePlayers(CircularList<Player> players, List<Monument> monuments){
        this.players = players;
        this.monuments = monuments;
        setMonumentsToPlayers();
        initPlayerState();
        initPlayerResources();
    }

    private void setMonumentsToPlayers(){
        for (int i=0; i<players.size(); i++){
            Player p = players.get(i);
            p.setMonument(monuments.get(i));
        }
    }

    private void initPlayerState(){
        for(Player player : players){
            player.setState(new PlayerState());
        }
    }

    private void initPlayerResources(){
        for (Player player : players){
            player.initResources();
        }
    }

    /**
     * This method takes in a list of players who has not yet been assigned their left neighbour, right neighbour and monument
     * Return a list where each player is assigned their neighbours and a random monument (all players will have a different monument)
     * @param players
     * @param monuments
     * @return
     */
    public static CircularList<Player> getInitializedPlayers(CircularList<Player> players, List<Monument> monuments){
        InitializePlayers initializePlayers = new InitializePlayers(players, monuments);
        return initializePlayers.players;
    }
}
