package com.ESSBG.app.Model.Card;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class CardTest {

    @Before
    public void setup(){}

    @Test
    public void testGetColor(){
        Card c = new EitherResourceCard("Test", List.of(ResourceEnum.WAR), ColorEnum.BLUE, null);
        assertEquals(ColorEnum.BLUE, c.getColor());
    }

    @Test
    public void testGetCost(){
        Card c = new EitherResourceCard("Test", List.of(ResourceEnum.WAR), ColorEnum.BLUE, null);
        assertEquals(List.of(ResourceEnum.WAR), c.getCost());
    }

    @Test
    public void testGetName(){
        Card c = new EitherResourceCard("Test", List.of(ResourceEnum.WAR), ColorEnum.BLUE, null);
        assertEquals("Test", c.getName());
    }
}
