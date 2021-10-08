package com.ESSBG.app.Model.Action.Handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.INeighborReductionAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Player.PlayerState;

import org.junit.Before;
import org.junit.Test;

public class NeighborReductionHandlerTest {
    
    PlayerState state;
    INeighborReductionHandler handler;
    INeighborReductionAction action;
    ArrayList<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        NeighborReductionCard card = new NeighborReductionCard("Test", resourceEnumList, ColorEnum.BLUE, action);
        state = new PlayerState();
        state.addReductionCard(card);
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.CLAY);
        resourceEnumList.add(ResourceEnum.STONE);
        action = new NeighborReductionAction(resourceEnumList);
        handler = new NeighborReductionHandler(action);    
    }

    @Test
    public void statesNotEqual(){
        PlayerState updatedState = new PlayerState(state);
        updatedState = handler.updateState(updatedState);
        assertNotEquals(updatedState, state);
    }

    @Test
    public void updatedStateIsUpdated(){
        List<ResourceEnum> list = new ArrayList<>(resourceEnumList);
        PlayerState updatedState = handler.updateState(state);
        assertEquals(updatedState.getNeighborReductions(), list);
    }
}
