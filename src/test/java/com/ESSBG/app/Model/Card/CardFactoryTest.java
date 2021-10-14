package com.ESSBG.app.Model.Card;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.CardFactory;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardFactoryTest {
    List<List<Card>> cardList;

    @Before
    public void init() {
        cardList = CardFactory.generateHands(1, 7, 7);
    }

    @Test
    public void testGenerateHandsSevenPlayers() {
        int count = 0;
        for (List<Card> list : cardList) {
            count += list.size();
        }

        assertEquals(49, count);
    }

    @Test
    public void testGenerateHandsSixPlayers() {
        List<List<Card>> listTmp = CardFactory.generateHands(2, 6, 4);
        int count = 0;
        for (List<Card> list : listTmp) {
            count += list.size();
        }

        assertEquals(24, count);
    }

    @Test
    public void testGenerateHandsTwoPlayers() {
        List<List<Card>> listTmp = CardFactory.generateHands(3, 3, 3);
        int count = 0;
        for (List<Card> list : listTmp) {
            count += list.size();
        }

        assertEquals(9, count);
    }

    @Test
    public void testNrOfTotalCards() {
        assertEquals(49, CardFactory.getNrOfTotalCards());
    }


    @Test
    public void testCorrectAmountOfColorCards() {
        int countBrown = 0;
        int countGreen = 0;
        int countRed = 0;
        int countYellow = 0;
        int countGrey = 0;
        int countBlue = 0;

        for (List<Card> cards : cardList) {
            for (Card c : cards) {
                if (c.getColor() == ColorEnum.BROWN) {
                    countBrown++;
                } else if (c.getColor() == ColorEnum.GREEN) {
                    countGreen++;
                } else if (c.getColor() == ColorEnum.RED) {
                    countRed++;
                } else if (c.getColor() == ColorEnum.BLUE) {
                    countBlue++;
                } else if (c.getColor() == ColorEnum.GRAY) {
                    countGrey++;
                } else if (c.getColor() == ColorEnum.YELLOW) {
                    countYellow++;
                }
            }
        }


        assertEquals(CardFactory.nrOfBasicResCards + CardFactory.nrOfEitherCards / 2, countBrown);
        assertEquals(CardFactory.nrOfBasicLuxuryResCards, countGrey);
        assertEquals(CardFactory.nrOfCoinCards + CardFactory.nrOfNeighborRedCards + CardFactory.nrOfEitherCards / 2, countYellow);
        assertEquals(CardFactory.nrOfWarCards, countRed);
        assertEquals(CardFactory.nrOfGreenCards, countGreen);
        assertEquals(CardFactory.nrOfVicPointsCards, countBlue);
    }

    @Test
    public void testRedCards() {
        int age = 1;
        for (List<Card> list : cardList) {
            for (Card c : list) {
                if (c.getColor() == ColorEnum.RED) {
                    assertTrue(c.getCost().size() == 0);
                    assertEquals("War wagon", c.getName());
                }
            }
        }

        age++;
        List<List<Card>> ageTwoList = CardFactory.generateHands(age, 6, 4);
        for (List<Card> list : ageTwoList) {
            for (Card c : list) {
                if (c.getColor() == ColorEnum.RED) {
                    assertTrue(c.getCost().size() == age + 1);
                    assertEquals("War wagon", c.getName());
                }
            }
        }
    }

    @Test
    public void testBasicResourceCards() {
        int age = 1;
        for (List<Card> list : cardList) {
            for (Card c : list) {
                if (c.getColor() == ColorEnum.BROWN && c.getName().equals("Resource")) {
                    assertTrue(c.getCost().size() == age - 1);
                }
            }
        }

        age = 7;
        List<List<Card>> ageSevenList = CardFactory.generateHands(age, 5, 7);
        for (List<Card> list : ageSevenList) {
            for (Card c : list) {
                if (c.getColor() == ColorEnum.BROWN && c.getName().equals("Resource")) {
                    assertTrue(c.getCost().size() == age - 1);
                }
            }
        }
    }

    @Test
    public void testEitherResources() {
        int age = 1;
        for (List<Card> list : cardList) {
            for (Card c : list) {
                if (c.getName() == "Caravansery" || c.getName() == "Forum") {
                    assertTrue(c.getCost().size() == 2);
                } else if (c.getName() == "Timber yard" || c.getName() == "Clay pit" || c.getName() == "Mine") {
                    assertTrue(c.getCost().size() == age - 1);
                }
            }
        }

        age = 5;
        List<List<Card>> ageFiveList = CardFactory.generateHands(age, 4, 5);
        for (List<Card> list : ageFiveList) {
            for (Card c : list) {
                if (c.getName() == "Caravansery" || c.getName() == "Forum") {
                    assertTrue(c.getCost().size() == 2);
                } else if (c.getName() == "Timber yard" || c.getName() == "Clay pit" || c.getName() == "Mine") {
                    assertTrue(c.getCost().size() == age - 1);
                }
            }
        }
    }

    @Test
    public void testGreenResources() {
        int age = 1;
        for (List<Card> list : cardList) {
            for (Card c : list) {
                if (c.getColor() == ColorEnum.GREEN){
                    assertTrue(c.getCost().size() == 1);
                }
            }
        }

        age = 3;
        List<List<Card>> ageThreeList = CardFactory.generateHands(age, 4, 5);
        for (List<Card> list : ageThreeList) {
                for (Card c : list) {
                    if (c.getColor() == ColorEnum.GREEN){
                        assertTrue(c.getCost().size() == age+1);
                    }
                }

        }
    }
}
