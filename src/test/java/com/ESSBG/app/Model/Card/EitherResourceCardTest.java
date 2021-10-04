package com.ESSBG.app.Model.Card;

import java.util.List;
import java.util.ArrayList;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Player.PlayerState;

import org.junit.Before;
import org.junit.Test;

public class EitherResourceCardTest {
    
    EitherResourceCard card;
    PlayerState state;

    @Before
    public void setup(){
        state = new PlayerState();
        List<ResourceEnum> resourceEnumList = new ArrayList<>();
        resourceEnumList.add(ResourceEnum.STONE);
        resourceEnumList.add(ResourceEnum.CLAY);
        card = new EitherResourceCard("Test", resourceEnumList, ColorEnum.GRAY, new EitherResourceAction(resourceEnumList));
    }

}
