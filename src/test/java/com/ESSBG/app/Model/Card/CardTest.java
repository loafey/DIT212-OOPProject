package com.ESSBG.app.Model.Card;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class CardTest {

    Card c;

    @Before
    public void setup(){
        new EitherResourceCard("Test", List.of(ResourceEnum.WAR), ColorEnum.BLUE, null);
    }

    @Test
    public void testGetColor(){
        assertEquals(ColorEnum.BLUE, c.getColor());
    }

    @Test
    public void testGetCost(){
        assertEquals(List.of(ResourceEnum.WAR), c.getCost());
    }

    @Test
    public void testGetName(){
        assertEquals("Test", c.getName());
    }
}
