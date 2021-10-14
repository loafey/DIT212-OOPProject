package com.ESSBG.app.Model.Card;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.CardFactory;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardFactoryTest {
    List<List<Card>> cardList;
    @Before
    public void init(){
        cardList = CardFactory.generateHands(1, 7, 7);
    }

    @Test
    public void testCorrectAmountOfCards(){
        int count = 0;


        for (List<Card> list : cardList){
            for (Card card : list){
                count++;
            }
        }

        assertEquals(49, count);
    }


    @Test
    public void testCorrectAmountOfColorCards(){
        int countBrown = 0;
        int countGreen = 0;
        int countRed = 0;
        int countYellow = 0;
        int countGrey = 0;
        int countBlue = 0;

        for (List<Card> cards : cardList){
            for (Card c : cards){
                if (c.getColor() == ColorEnum.BROWN){
                    countBrown++;
                }
                else if (c.getColor() == ColorEnum.GREEN){
                    countGreen++;
                }
                else if (c.getColor() == ColorEnum.RED){
                    countRed++;
                }
                else if (c.getColor() == ColorEnum.BLUE){
                    countBlue++;
                }
                else if (c.getColor() == ColorEnum.GRAY){
                    countGrey++;
                }
                else if (c.getColor() == ColorEnum.YELLOW){
                    countYellow++;
                }
            }
        }


        assertEquals(CardFactory.nrOfBasicResCards + CardFactory.nrOfEitherCards/2, countBrown);
        assertEquals(CardFactory.nrOfBasicLuxuryResCards, countGrey);
        assertEquals(CardFactory.nrOfCoinCards + CardFactory.nrOfNeighborRedCards + CardFactory.nrOfEitherCards/2, countYellow);
        assertEquals(CardFactory.nrOfWarCards, countRed);
        assertEquals(CardFactory.nrOfGreenCards, countGreen);
        assertEquals(CardFactory.nrOfVicPointsCards, countBlue);
    }
}
