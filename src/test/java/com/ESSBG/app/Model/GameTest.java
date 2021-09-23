package com.ESSBG.app.Model;

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
        List<String> types = new ArrayList<>();
        types.add("Wood");
        int amountTestPLayers = 3;
        for (int i = 0; i <amountTestPLayers; i++) {
            ResourceCard rCard0 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
            ResourceCard rCard1 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
            ResourceCard rCard2 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
            ResourceCard rCard3 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
            List<AbstractCard> monCardList = new ArrayList<>();
            monCardList.add(rCard1);
            monCardList.add(rCard2);
            monCardList.add(rCard3);
            Monument mon = new Monument("test", rCard0, monCardList);
            testPlayers.add(new Player(0, "testPlayer", mon));
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
    }
}
