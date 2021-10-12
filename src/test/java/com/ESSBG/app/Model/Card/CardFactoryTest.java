package com.ESSBG.app.Model.Card;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.CardFactory;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardFactoryTest {
    List<List<Card>> cardList = CardFactory.getCards();

    @Test
    public void testCorrectAmountOfCards(){
        assertEquals(3, cardList.size());

        for (List<Card> list : cardList){
            assertEquals(7, list.size());
        }
    }

    //TODO kolla så resource inte är null, kan inte just nu utan att ändra en del
    @Test
    public void testValidResourceCards(){
        for (List<Card> list : cardList){
            for (Card card : list){
                assertNotNull(card.getColor());
                assertNotNull(card.getCardTypeEnum());
            }
        }
    }
}
