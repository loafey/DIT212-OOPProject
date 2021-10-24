package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.Monuments.Alexandria;
import com.ESSBG.app.Model.ResourceEnum;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Gabriel Hagström
 */
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


/*
    @Test
    public void buildStageOfMonument() {
        p.setMonument(new Alexandria());
        p.initResources();

        assertEquals(4, p.getState().getGuaranteedResources().size());

        // Add stone to be able to build first upgrade
        List<ResourceEnum> list = p.getState().getGuaranteedResources();
        list.add(ResourceEnum.STONE);
        list.add(ResourceEnum.STONE);

        PlayerState s = p.getState();
        s.setGuaranteedResources(list);
        p.setState(s);


        // First upgrade, should give 3 additional resources (plus the stone)
        p.buildStageOfMonument();

        assertEquals(9, p.getState().getGuaranteedResources().size());
        assertEquals(1, p.getMonument().getStageBuilt());


        // Trying to upgrade, should not upgrade because we don't have enough resources
        p.buildStageOfMonument();

        assertEquals(9, p.getState().getGuaranteedResources().size());
        assertEquals(1, p.getMonument().getStageBuilt());


        // Second upgrade with efficient resources, should give 4 additional resources (plus the wood)
        List<ResourceEnum> list2 = p.getState().getGuaranteedResources();
        list2.add(ResourceEnum.WOOD);
        list2.add(ResourceEnum.WOOD);

        PlayerState s2 = p.getState();
        s2.setGuaranteedResources(list2);
        p.setState(s2);

        p.buildStageOfMonument();

        assertEquals(15, p.getState().getGuaranteedResources().size());
        assertEquals(2, p.getMonument().getStageBuilt());


        //Third upgrade, should give 7 additional victory points (plus the glass)
        List<ResourceEnum> list3 = p.getState().getGuaranteedResources();
        list3.add(ResourceEnum.GLASS);
        list3.add(ResourceEnum.GLASS);

        PlayerState s3 = p.getState();
        s3.setGuaranteedResources(list3);
        p.setState(s3);

        p.buildStageOfMonument();

        assertEquals(24, p.getState().getGuaranteedResources().size());
        assertEquals(3, p.getMonument().getStageBuilt());
    }

 */


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
