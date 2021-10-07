package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.Monuments.Alexandria;
import com.ESSBG.app.Model.ResourceEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player(0, new PlayerState());

    }

    @Test
    public void testEquals() {
        assertEquals(p, p);
        assertNotEquals(p, new Player(1, new PlayerState()));
    }

    @Test
    public void testGetId(){
        assertEquals(0, p.getId());
    }

    @Test
    public void testGetState(){
        assertNotNull(p.getState());
    }

    @Test
    public void testSetState() {
        PlayerState newState = new PlayerState();
        p.setState(newState);
        assertEquals(p.getState(), newState);
    }

    @Test
    public void testSetAndGetMonument() {
        Monument mon = new Alexandria();
        p.setMonument(mon);
        assertEquals(p.getMonument(), mon);
    }

    @Test
    public void testInitPlayerResources(){
        p.setMonument(new Alexandria());
        p.initResources();

        assertEquals(3, p.getState().getCoins());
        assertEquals(4, p.getState().getGuaranteedResources().size());
        assertTrue(p.getState().getGuaranteedResources().contains(ResourceEnum.GLASS));
    }

    @Test
    public void buildStageOfMonument() {
        p.setMonument(new Alexandria());
        p.initResources();


        int nOfCoins = p.getState().getCoins();
        int amountOfGlass = 1;

        // First upgrade, should give 3 victory points
        List<ResourceEnum> list = p.getState().getGuaranteedResources();
        list.add(ResourceEnum.STONE);
        list.add(ResourceEnum.STONE);

        PlayerState s = p.getState();
        s.setGuaranteedResources(list);
        p.setState(s);


        p.buildStageOfMonument();

        assertEquals(7, p.getState().getGuaranteedResources().size());
        assertEquals(1, p.getMonument().getStageBuilt());


        // Second upgrade, should add four resources
        p.buildStageOfMonument();

        assertEquals(11, p.getState().getGuaranteedResources().size());
        assertEquals(2, p.getMonument().getStageBuilt());


        //Third upgrade, should add 7 victory points
        p.buildStageOfMonument();

        assertEquals(18,  p.getState().getGuaranteedResources().size());
        assertEquals(3, p.getMonument().getStageBuilt());
    }



    @Test
    public void initResources() {
        Monument mon = new Alexandria();
        p.setMonument(mon);
        p.initResources();

        int coins = 0;
        int monResource = 0;
        for (ResourceEnum resource : p.getState().getGuaranteedResources()) {
            if (resource == ResourceEnum.COIN) {
                coins++;
            }
            else if (resource == mon.getStartingResource()) {
                monResource++;
            }
        }

        assertEquals(3, coins);
        assertEquals(1, monResource);
    }
}