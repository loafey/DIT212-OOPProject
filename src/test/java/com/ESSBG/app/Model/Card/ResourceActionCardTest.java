package com.ESSBG.app.Model.Card;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.ResourceActionCard;

import org.junit.Before;
import org.junit.Test;


public class ResourceActionCardTest {
    
    ResourceActionCard card;
    IResourceAction action;

    @Before
    public void setup(){
        List<ResourceEnum> resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        resourceEnumList.add(ResourceEnum.CLAY);
        action = new ResourceAction(resourceEnumList);
        card = new ResourceActionCard("Test", resourceEnumList, ColorEnum.YELLOW, action);
    }

    @Test
    public void testGetAction(){
        assertEquals(card.getAction().getClass(), action.getClass());
    }
}
