package com.ESSBG.app.Model.Action.Handlers;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Player.PlayerState;

import org.junit.Before;

public class EitherHandlerTest {

    PlayerState state;
    IEitherHandler handler;
    IEitherAction action;
    List<ResourceEnum> resourceEnumList;

    @Before
    public void setup(){
        state = new PlayerState();
        resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.CLAY);
        resourceEnumList.add(ResourceEnum.STONE);
        action = new EitherResourceAction(resourceEnumList);
        handler = new EitherHandler(action);    
    }
}
