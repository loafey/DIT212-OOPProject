package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.ResourceEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerStateTest {

    PlayerState playerState;
    EitherResourceCard eitherResourceCard;
    NeighborReductionCard neighborReductionCard;
    ResourceActionCard resourceActionCard;

    @Before
    public void setUp() throws Exception {
        playerState = new PlayerState();
        List<ResourceEnum> resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        eitherResourceCard = new EitherResourceCard("test", new ArrayList<>(resourceEnumList), ColorEnum.BROWN, new EitherResourceAction(new ArrayList<>(resourceEnumList)));
        neighborReductionCard = new NeighborReductionCard("test", new ArrayList<>(resourceEnumList), ColorEnum.BROWN, new NeighborReductionAction(new ArrayList<>(resourceEnumList)));
        resourceActionCard = new ResourceActionCard("test", new ArrayList<>(resourceEnumList), ColorEnum.BROWN, new ResourceAction(new ArrayList<>(resourceEnumList)));
    }

    @Test
    public void testEquals() {
        playerState.addCoins(10);
        PlayerState newState = new PlayerState(playerState);
        assertEquals(playerState, newState);
        playerState.addWinPoints(1);
        assertNotEquals(playerState, newState);
    }

    @Test
    public void playerStateConstructor() {
        ArrayList<ResourceEnum> resourceEnums = new ArrayList<>();
        resourceEnums.add(ResourceEnum.STONE);
        playerState.addPlayedCard(eitherResourceCard);
        playerState.addPlayedCard(neighborReductionCard);
        playerState.addPlayedCard(resourceActionCard);
        playerState.addGuaranteedResource(ResourceEnum.STONE);
        playerState.addEitherResource(resourceEnums);
        playerState.setNeighborReductions(resourceEnums);
        playerState.addWinPoints(20);
        playerState.addLosePoints(10);
        PlayerState newPlayerState = new PlayerState(playerState);
        assertEquals(playerState, newPlayerState);
    }

    @Test
    public void addCard() {
        playerState.addPlayedCard(eitherResourceCard);
        assertEquals(eitherResourceCard, playerState.getPlayedCards().get(0));
    }

    @Test
    public void addGuaranteedResource() {
        playerState.addGuaranteedResource(ResourceEnum.STONE);
        assertEquals(ResourceEnum.STONE, playerState.getGuaranteedResources().get(0));
    }

    @Test
    public void addEitherResource() {
        ArrayList<ResourceEnum> resourceEnums = new ArrayList<>();
        resourceEnums.add(ResourceEnum.STONE);
        playerState.addEitherResource(resourceEnums);
        assertEquals(resourceEnums, playerState.getEitherResources().get(0));
    }

    @Test
    public void addCoins() {
        playerState.addCoins(1337);
        assertEquals(1337, playerState.getCoins());
        int coinCount = 0;
        for (ResourceEnum guaranteedResource : playerState.getGuaranteedResources()) {
            if (guaranteedResource == ResourceEnum.COIN) {
                coinCount++;
            }
        }
        assertEquals(1337, coinCount);
    }

    @Test
    public void addWinPoints() {
        playerState.addWinPoints(1337);
        assertEquals(1337, playerState.getWinPoints());
    }

    @Test
    public void addLosePoints() {
        playerState.addLosePoints(1337);
        assertEquals(1337, playerState.getLosePoints());
    }


    @Test
    public void setPlayedCards() {
        playerState.addPlayedCard(eitherResourceCard);
        playerState.setPlayedCards(new ArrayList<>());
        assertEquals(0, playerState.getPlayedCards().size());
    }

    @Test
    public void setGuaranteedResources() {
        playerState.addGuaranteedResource(ResourceEnum.STONE);
        playerState.setGuaranteedResources(new ArrayList<>());
        assertEquals(0, playerState.getGuaranteedResources().size());
    }

    @Test
    public void setEitherResources() {
        ArrayList<ResourceEnum> resourceEnums = new ArrayList<>();
        resourceEnums.add(ResourceEnum.STONE);
        playerState.addEitherResource(resourceEnums);
        playerState.setEitherResources(new ArrayList<>());
        assertEquals(0, playerState.getEitherResources().size());
    }

    @Test
    public void setNeighborReductions() {
        List<ResourceEnum> l = new ArrayList<>();
        l.add(ResourceEnum.STONE);
        l.add(ResourceEnum.STONE);
        l.add(ResourceEnum.STONE);
        playerState.setNeighborReductions(l);
        assertArrayEquals(l.toArray(), playerState.getNeighborReductions().toArray());
    }

    @Test
    public void setWinPoints() {
        playerState.setWinPoints(1337);
        assertEquals(1337, playerState.getWinPoints());
    }

    @Test
    public void setLosePoints() {
        playerState.setLosePoints(1337);
        assertEquals(1337, playerState.getLosePoints());
    }

    @Test
    public void setWarTokens() {
        playerState.setWarTokens(1337);
        assertEquals(1337, playerState.getWarTokens());
    }


}