package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.Monuments.Alexandria;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MonumentTest {
    Monument monument = new Alexandria();


    @Test
    public void testName(){
        assertEquals("Alexandria", monument.getName());
    }

    @Test
    public void testStartingResource(){
        assertEquals(ResourceEnum.GLASS, monument.getStartingResource());
    }

    @Test
    public void testResourcesToBuildStage1(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.STONE);
        tmp.add(ResourceEnum.STONE);
        assertEquals(tmp, monument.getResourcesToBuildStage1());
    }

    @Test
    public void testResourcesToBuildStage2(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.WOOD);
        tmp.add(ResourceEnum.WOOD);
        assertEquals(tmp, monument.getResourcesToBuildStage2());
    }

    @Test
    public void testResourcesToBuildStage3(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.GLASS);
        tmp.add(ResourceEnum.GLASS);
        assertEquals(tmp, monument.getResourcesToBuildStage3());
    }

    @Test
    public void testStage2Reward(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.CLAY);
        tmp.add(ResourceEnum.ORE);
        tmp.add(ResourceEnum.WOOD);
        tmp.add(ResourceEnum.STONE);
        assertEquals(tmp, monument.stage2Reward());
    }

}
