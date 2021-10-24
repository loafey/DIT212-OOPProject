package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class InitializePlayersTest {
    Player A = new Player(0, null);
    Player B = new Player(1, null);
    Player C = new Player(2, null);
    Player D = new Player(3, null);
    CircularList<Player> playersTmp = new CircularList<>();
    List<Player> players = new ArrayList<>();

    @Before
    public void init(){
        playersTmp.add(A);
        playersTmp.add(B);
        playersTmp.add(C);
        playersTmp.add(D);

        players = InitializePlayers.getInitializedPlayers(playersTmp, MonumentFactory.getMonuments());
    }


    @Test
    public void testMonumentsNotNull(){
        for (Player p : players){
            assertNotNull(p.getMonument());
        }
    }

    @Test
    public void testPlayerStateAndResources(){
        for (Player p : players){
            assertNotNull(p.getState());
            //assertEquals(3, p.getState().getCoins());
            int expected = 4;
            int actual = p.getState().getGuaranteedResources().size();
            assertEquals(expected, actual);
        }
    }

}
