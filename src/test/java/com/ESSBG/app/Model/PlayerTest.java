package com.ESSBG.app.Model;

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
        List<String> types = new ArrayList<String>();
        types.add("Wood");

        ResourceCard rCard0 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
        ResourceCard rCard1 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
        ResourceCard rCard2 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
        ResourceCard rCard3 = new ResourceCard(new HashMap<String, Integer>(), ColorEnum.BROWN, types);
        List<AbstractCard> monCardList = new ArrayList<>();
        monCardList.add(rCard1);
        monCardList.add(rCard2);
        monCardList.add(rCard3);
        Monument mon = new Monument("test", rCard0, monCardList);
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
        assertArrayEquals(new ResourceCard[]{}, player.getCardList().toArray());
    }

    // TODO add a test for addCard that reach makeTrade for-loop
    @Test
    public void testAddCard() {
        Map<String, Integer> map = new HashMap<>();
        List<String> types = new ArrayList<>();
        types.add("Wood");
        ResourceCard card = new ResourceCard(map, ColorEnum.GREEN, types);
        player.addCard(card);
        assertTrue(player.addCard(card));
    }

    @Test
    public void testAddCardWithNotEnoughBalance() {
        Map<String, Integer> map = new HashMap<>();
        List<String> types = new ArrayList<>();
        types.add("Wood");
        map.put("Wood", -10);
        ResourceCard card = new ResourceCard(map, ColorEnum.GREEN, types);
        player.addCard(card);
        assertFalse(player.addCard(card));
    }

    @Test
    public void testAddCardWithEnoughBalance() {
        Map<String, Integer> map = new HashMap<>();
        List<String> types = new ArrayList<>();
        types.add("Wood");
        map.put("Wood", 5);
        ResourceCard card = new ResourceCard(map, ColorEnum.BROWN, types);
        assertTrue(player.addCard(card));
    }

    @Test
    public void testGetCardListWithCards() {
        Map<String, Integer> map = new HashMap<>();
        List<String> types = new ArrayList<>();
        types.add("Wood");
        ResourceCard card1 = new ResourceCard(map, ColorEnum.GREEN, types);
        ResourceCard card2 = new ResourceCard(map, ColorEnum.GREEN, types);
        ResourceCard card3 = new ResourceCard(map, ColorEnum.GREEN, types);
        List<AbstractCard> l = new ArrayList<>();
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