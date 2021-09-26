package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameTest {

    Game game;
    List<Player> testPlayers;

    @Before
    public void setup() {
        game = new Game();
        testPlayers = new ArrayList<>();
        List<IAction> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(Resource.WOOD)));
        int amountTestPLayers = 3;
        for (int i = 0; i <amountTestPLayers; i++) {
            Monument mon = new Monument("Monaden", List.of(Resource.ORE), monAction);
            testPlayers.add(new Player(i, mon));
        }
    }

    @Test
    public void testUpdatePlayerNeighbors() {
        game.players.add(testPlayers.get(0));
        game.players.add(testPlayers.get(1));
        game.players.add(testPlayers.get(2));
        game.updatePlayerNeighbors();
        assertEquals(game.players.get(0).getLeftPlayer(), testPlayers.get(2));
        assertEquals(game.players.get(0).getRightPlayer(), testPlayers.get(1));
        assertEquals(game.players.get(1).getLeftPlayer(), testPlayers.get(0));
        assertEquals(game.players.get(1).getRightPlayer(), testPlayers.get(2));
        assertEquals(game.players.get(2).getLeftPlayer(), testPlayers.get(1));
        assertEquals(game.players.get(2).getRightPlayer(), testPlayers.get(0));
    }
}
