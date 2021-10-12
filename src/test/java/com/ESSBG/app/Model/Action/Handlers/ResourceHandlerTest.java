package com.ESSBG.app.Model.Action.Handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.Player.PlayerState;

import org.junit.Before;
import org.junit.Test;

public class ResourceHandlerTest {
    

    PlayerState state;
    IResourceHandler handler;
    IResourceAction action;
    ArrayList<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        ResourceActionCard card = new ResourceActionCard("Test", resourceEnumList, ColorEnum.BLUE, handler);
        state = new PlayerState();
        state.addResourceCard(card);
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.CLAY);
        resourceEnumList.add(ResourceEnum.STONE);
        action = new ResourceAction(resourceEnumList);
        handler = new ResourceHandler(action);
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
        assertEquals(updatedState.getGuaranteedResources(), list);
    }
}
