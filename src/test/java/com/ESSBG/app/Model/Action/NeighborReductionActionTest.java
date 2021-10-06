package com.ESSBG.app.Model.Action;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;

import org.junit.Before;
import org.junit.Test;

public class NeighborReductionActionTest {
    
    private INeighborReductionAction action;
    private List<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        resourceEnumList.add(ResourceEnum.CLAY);
        action = new NeighborReductionAction(resourceEnumList);
    }
    
    @Test
    public void testGetList(){
        assertEquals(action.getList(), resourceEnumList);
    }
}
