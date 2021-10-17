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
    public void testBuildStage(){
        monument.buildStage();
        assertEquals(1, monument.getStageBuilt());
        monument.buildStage();
        assertEquals(2, monument.getStageBuilt());
        monument.buildStage();
        assertEquals(3, monument.getStageBuilt());
    }

    @Test
    public void testGetStageBuilt(){
        assertEquals(0, monument.getStageBuilt());
        monument.buildStage();
        assertEquals(1, monument.getStageBuilt());
    }

    @Test
    public void testStartingResource(){
        assertEquals(ResourceEnum.GLASS, monument.getStartingResource());
    }

    @Test
    public void testGetResourcesToBuildStage1(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.STONE);
        tmp.add(ResourceEnum.STONE);
        assertEquals(tmp, monument.getResourcesToBuildStage1());
    }

    @Test
    public void testGetResourcesToBuildStage2(){
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
    public void getCostToBuildNextStage(){
        // Before building any stage
        List<ResourceEnum> cost = monument.getCostToBuildNextStage();
        List<ResourceEnum> list = monument.getResourcesToBuildStage1();

        assertEquals(cost,list);

        // Building stage one
        monument.buildStage();

        List<ResourceEnum> cost2 = monument.getCostToBuildNextStage();
        List<ResourceEnum> list2 = monument.getResourcesToBuildStage2();

        assertEquals(cost2,list2);


        // Building stage two
        monument.buildStage();

        List<ResourceEnum> cost3 = monument.getCostToBuildNextStage();
        List<ResourceEnum> list3 = monument.getResourcesToBuildStage3();

        assertEquals(cost3,list3);
    }


    @Test
    public void getRewardForBuildingNextStage(){
        // Before building any stage
        List<ResourceEnum> cost = monument.getRewardForBuildingNextStage();
        List<ResourceEnum> list = monument.getStage1Reward();

        assertEquals(cost,list);

        // Building stage one
        monument.buildStage();

        List<ResourceEnum> cost2 = monument.getRewardForBuildingNextStage();
        List<ResourceEnum> list2 = monument.getStage2Reward();

        assertEquals(cost2,list2);


        // Building stage two
        monument.buildStage();

        List<ResourceEnum> cost3 = monument.getRewardForBuildingNextStage();
        List<ResourceEnum> list3 = monument.getStage3Reward();

        assertEquals(cost3,list3);
    }





    @Test
    public void testGetStage2Reward(){
        List<ResourceEnum> tmp = new ArrayList<>();
        tmp.add(ResourceEnum.CLAY);
        tmp.add(ResourceEnum.ORE);
        tmp.add(ResourceEnum.WOOD);
        tmp.add(ResourceEnum.STONE);
        assertEquals(tmp, monument.getStage2Reward());
    }

}
