package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Action.IResource;
import com.ESSBG.app.Model.Action.ResourceAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        List <Resource> generation = new ArrayList<>();
        generation.add(Resource.WOOD);
        List<IResource> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(Resource.ORE)));
        Monument mon = new Monument("Monaden", generation, monAction);
        player = new Player(0, mon);
    }

    @Test
    public void testTestGetName() {
        player.setName("testPlayer");
        assertEquals("testPlayer", player.getName());
    }

    @Test
    public void testTestSetName() {
        player.setName("test");
        assertEquals("test", player.getName());
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