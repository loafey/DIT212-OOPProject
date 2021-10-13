package com.ESSBG.app.Model.Card;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.ColorEnum;

import org.junit.Before;
import org.junit.Test;

public class EitherResourceCardTest {
    
    EitherResourceCard card;
    IEitherAction action;

    @Before
    public void setup(){
        List<ResourceEnum> resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        resourceEnumList.add(ResourceEnum.CLAY);
        action = new EitherResourceAction(resourceEnumList);
        card = new EitherResourceCard("Test", resourceEnumList, ColorEnum.GRAY, action);
    }

    @Test
    public void testGetAction(){
        assertEquals(action.getClass(), card.getAction().getClass());
    }
}
