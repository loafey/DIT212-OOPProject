package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.ConcurrentCircularList;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class InitializePlayers {
    private List<Monument> monuments;
    private ConcurrentCircularList<Player> players;

    private InitializePlayers(ConcurrentCircularList<Player> players, List<Monument> monuments){
        this.players = players;
        this.monuments = monuments;
        setMonumentsToPlayers();
        updatePlayerNeighbors();
        initPlayerState();
        initPlayerResources();
    }

    private void setMonumentsToPlayers(){
        for (int i=0; i<players.size(); i++){
            Player p = players.get(i);
            p.setMonument(monuments.get(i));
        }
    }

    private void updatePlayerNeighbors() {
        for (Player player : players) {
            player.setLeftNeighbor(players.getPrevious(player));
            player.setRightNeighbor(players.getNext(player));

        }
    }

    private void initPlayerState(){
        for(Player player : players){
            player.setState(new PlayerState(player.getLeftNeighbor(), player.getRightNeighbor()));
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
    public static ConcurrentCircularList<Player> getInitializedPlayers(ConcurrentCircularList<Player> players, List<Monument> monuments){
        InitializePlayers initializePlayers = new InitializePlayers(players, monuments);
        return initializePlayers.players;
    }
}
