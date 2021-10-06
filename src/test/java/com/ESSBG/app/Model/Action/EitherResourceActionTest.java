package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class EitherResourceActionTest {
    
    private IEitherAction action;
    List<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.CLAY);
        resourceEnumList.add(ResourceEnum.STONE);
        action = new EitherResourceAction(resourceEnumList);
    }

    @Test
    public void testGetList(){
        assertEquals(action.getList(),resourceEnumList);
    }
}
