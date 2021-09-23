package com.ESSBG.app.Model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ESSBG.app.Model.ColorEnum.BROWN;
import static com.ESSBG.app.Model.ColorEnum.BLUE;

import static org.junit.Assert.*;


public class CardTest {

    private Player player;

    @Before
    public void setup(){

        HashMap<String, Integer> cost = new HashMap<>();

        List<AbstractCard> monCardList = new ArrayList<>();
        List<String> resource = new ArrayList<String>();

        ResourceCard resourceCard = new ResourceCard(cost, BROWN, resource);
        CoinCard coinCard = new CoinCard(cost, BLUE, 4);

        cost.put("Papyrus",1);
        cost.put("Wood",2);
        resource.add("Wood");

        monCardList.add(coinCard);
        monCardList.add(resourceCard);
        monCardList.add(resourceCard);

        Monument monument = new Monument("Monaden", resourceCard, monCardList);

        player = new Player(0, "testPlayer", monument);
    }

    @Test
    public void takeCoinFromCoinCard(){
        int prevCoins = player.getCoins();
        HashMap<String, Integer> cost =  new HashMap<>();
        cost.put("Wood", 1);
        CoinCard c = new CoinCard(cost, BROWN, 3);
        player.setCoins(c.getCoins());
        assertTrue(c.hasGiven && player.getCoins() == prevCoins + 3);
    }
}
