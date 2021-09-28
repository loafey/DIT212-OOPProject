package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Monuments.Alexandria;
import com.ESSBG.app.Model.Monuments.Monument;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class GameTest {

    Game game;
    List<Player> testPlayers;

    @Before
    public void setup() {
        game = new Game();
        testPlayers = new ArrayList<>();
        Player p1 = new Player(0, new PlayerState(null));
        List<IResourceAction> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(ResourceEnum.WOOD)));
        int amountTestPLayers = 3;
        for (int i = 0; i <amountTestPLayers; i++) { 
            Player pi = new Player(i, new PlayerState(null));
            testPlayers.add(pi);
            Monument mon = new Alexandria(pi);
        }
    }

    @Test
    public void testUpdatePlayerNeighbors() {
        game.players.add(testPlayers.get(0));
        game.players.add(testPlayers.get(1));
        game.players.add(testPlayers.get(2));
        game.updatePlayerNeighbors();
        assertEquals(game.players.get(0).getState().getLeftPlayer(), testPlayers.get(2));
        assertEquals(game.players.get(0).getState().getRightPlayer(), testPlayers.get(1));
        assertEquals(game.players.get(1).getState().getLeftPlayer(), testPlayers.get(0));
        assertEquals(game.players.get(1).getState().getRightPlayer(), testPlayers.get(2));
        assertEquals(game.players.get(2).getState().getLeftPlayer(), testPlayers.get(1));
        assertEquals(game.players.get(2).getState().getRightPlayer(), testPlayers.get(0));
    }
}
