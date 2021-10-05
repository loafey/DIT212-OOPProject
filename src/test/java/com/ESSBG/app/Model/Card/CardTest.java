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

    Card card;

    @Before
    public void setup(){
        card = new EitherResourceCard("Test", List.of(ResourceEnum.WAR), ColorEnum.BLUE, null);
    }

    @Test
    public void testGetColor(){
        assertEquals(ColorEnum.BLUE, card.getColor());
    }

    @Test
    public void testGetCost(){
        assertEquals(List.of(ResourceEnum.WAR), card.getCost());
    }

    @Test
    public void testGetName(){
        assertEquals("Test", card.getName());
    }
}
