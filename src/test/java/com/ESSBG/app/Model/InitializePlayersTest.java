package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.system.CallbackI;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InitializePlayersTest {
    Player A = new Player(0, null, null, null);
    Player B = new Player(1, null, null, null);
    Player C = new Player(2, null, null,null);
    Player D = new Player(3, null, null, null);
    ConcurrentCircularList<Player> playersTmp = new ConcurrentCircularList(new ReentrantLock(true));
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
    public void testNeighbours(){
        assertEquals(D, A.getLeftNeighbor());
        assertEquals(B, A.getRightNeighbor());

        assertEquals(A, B.getLeftNeighbor());
        assertEquals(C, B.getRightNeighbor());

        assertEquals(B, C.getLeftNeighbor());
        assertEquals(D, C.getRightNeighbor());

        assertEquals(C, D.getLeftNeighbor());
        assertEquals(A, D.getRightNeighbor());
    }

    @Test
    public void testMonumentsNotNull(){
        for (Player p : players){
            assertNotNull(p.getMonument());
        }
    }

    @Test
    public void testPlayerState(){
        for (Player p : players){
            assertNotNull(p.getState());
            //assertEquals(3, p.getState().getCoins());
            assertEquals(4, p.getState().getGuaranteedResources().size());
        }
    }

}
