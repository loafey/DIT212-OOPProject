package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.Monuments.Alexandria;
import com.ESSBG.app.Model.ResourceEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
    public void setState() {
        PlayerState newState = new PlayerState();
        p.setState(newState);
        assertEquals(p.getState(), newState);
    }

    @Test
    public void setLeftNeighbor() {
        Player p2 = new Player(1, new PlayerState());
        p.setLeftNeighbor(p2);
        assertEquals(p.getLeftNeighbor(), p2);
    }

    @Test
    public void setRightNeighbor() {
        Player p2 = new Player(1, new PlayerState());
        p.setRightNeighbor(p2);
        assertEquals(p.getRightNeighbor(), p2);
    }

    @Test
    public void setMonument() {
        Monument mon = new Alexandria();
        p.setMonument(mon);
        assertEquals(p.getMonument(), mon);
    }

    @Test
    @Ignore
    public void buildStageOfMonument() {
        fail("Vet inte hur man ska testa");
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