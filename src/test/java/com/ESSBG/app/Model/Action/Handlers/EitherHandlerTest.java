package com.ESSBG.app.Model.Action.Handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Player.PlayerState;

import org.junit.Before;
import org.junit.Test;

public class EitherHandlerTest {

    PlayerState state;
    IEitherHandler handler;
    IEitherAction action;
    ArrayList<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        EitherResourceCard card = new EitherResourceCard("Test", resourceEnumList, ColorEnum.BLUE, handler);
        state = new PlayerState();
        state.addEitherCard(card);
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.CLAY);
        resourceEnumList.add(ResourceEnum.STONE);
        action = new EitherResourceAction(resourceEnumList);
        handler = new EitherHandler(action);    
    }

    @Test
    public void statesNotEqual(){
        PlayerState updatedState = new PlayerState(state);
        updatedState = handler.updateState(updatedState);
        assertNotEquals(updatedState, state);
    }

    @Test
    public void updatedStateIsUpdated(){
        List<ArrayList<ResourceEnum>> twoDList = new ArrayList<>();
        twoDList.add(resourceEnumList);
        PlayerState updatedState = handler.updateState(state);
        assertEquals(updatedState.getEitherResources(), twoDList);
    }
}
