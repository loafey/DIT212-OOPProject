package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Actions.IGameAction;
import com.ESSBG.app.Model.Actions.ResourceAction;
import com.ESSBG.app.Model.Actions.ResourceEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        IGameAction a = new ResourceAction(ResourceEnum.Brick, 1);
        List<IGameAction> l = new ArrayList<>();
        l.add(new ResourceAction(ResourceEnum.Brick, 1));
        l.add(new ResourceAction(ResourceEnum.Brick, 1));
        l.add(new ResourceAction(ResourceEnum.Brick, 1));
        Monument mon = new Monument("test", a, l);
        player = new Player("testPlayer", mon);
    }

    @Test
    public void testTestGetName() {
        assertEquals("testPlayer", player.getName());
    }

    @Test
    public void testTestSetName() {
        player.setName("test");
        assertEquals("test", player.getName());
    }

    @Test
    public void testGetEmptyCardList() {
        assertArrayEquals(new Card[]{}, player.getCardList().toArray());
    }

    // TODO add a test for addCard that reach makeTrade for-loop
    @Test
    public void testAddCard() {
        Map<String, Integer> map = new HashMap<>();
        Card card = new Card(map, ColorEnum.GREEN, new ResourceAction(ResourceEnum.Brick, 1));
        player.addCard(card);
        assertTrue(player.addCard(card));
    }

    @Test
    public void testAddCardWithNotEnoughBalance() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Wood", -10);
        Card card = new Card(map, ColorEnum.GREEN, new ResourceAction(ResourceEnum.Brick, 1));
        player.addCard(card);
        assertFalse(player.addCard(card));
    }

    @Test
    public void testGetCardListWithCards() {
        Map<String, Integer> map = new HashMap<>();
        Card card1 = new Card(map, ColorEnum.GREEN, new ResourceAction(ResourceEnum.Brick, 1));
        Card card2 = new Card(map, ColorEnum.GREEN, new ResourceAction(ResourceEnum.Brick, 1));
        Card card3 = new Card(map, ColorEnum.GREEN, new ResourceAction(ResourceEnum.Brick, 1));
        List<Card> l = new ArrayList<>();
        l.add(card1);
        l.add(card2);
        l.add(card3);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);
        assertArrayEquals(l.toArray(), player.getCardList().toArray());
    }

    @Test
    public void testGetPeacePoints() {
        assertEquals(0, player.getPeacePoints());
    }
    @Test
    public void testSetPeacePoints() {
        player.setPeacePoints(1337);
        assertEquals(1337, player.getPeacePoints());
    }
    @Test
    public void testGetWarPoints() {
        assertEquals(0, player.getWarPoints());
    }
    @Test
    public void testSetWarPoints() {
        player.setWarPoints(1337);
        assertEquals(1337, player.getWarPoints());
    }
}