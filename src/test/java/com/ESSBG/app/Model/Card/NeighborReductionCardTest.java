package com.ESSBG.app.Model.Card;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborReductionAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;

import org.junit.Before;
import org.junit.Test;

public class NeighborReductionCardTest {

    NeighborReductionCard card;
    INeighborReductionAction action;


    @Before
    public void setup(){
        List<ResourceEnum> resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        resourceEnumList.add(ResourceEnum.CLAY);
        action = new NeighborReductionAction(resourceEnumList);
        card = new NeighborReductionCard("Test", resourceEnumList, ColorEnum.YELLOW, action);
    }


    @Test
    public void testGetAction(){
        assertEquals(card.getAction().getClass(), action.getClass());
    }
}
