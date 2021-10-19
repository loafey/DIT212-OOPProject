package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.Player.Player;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class GameTest {

    Game game;
    List<Player> testPlayers;

    @Before
    public void setup() {
        game = new Game();
        testPlayers = new ArrayList<>();
//        Player p1 = new Player(0, new PlayerState(null));
        List<IResourceAction> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(ResourceEnum.WOOD)));
        int amountTestPLayers = 3;
        for (int i = 0; i <amountTestPLayers; i++) {
//            Player pi = new Player(i, new PlayerState(null));
//            testPlayers.add(pi);
//            Monument mon = new Alexandria(pi);
        }
        game.init();
    }



    @Test
    public void getRotatedCardsClockWise(){
        List<List<Card>> list = getMockCards();
        List<Card> A = list.get(0);
        List<Card> B = list.get(1);
        List<Card> C = list.get(2);

        List<List<Card>> expectedListAfterRotation = new ArrayList<>();
        expectedListAfterRotation.add(C);
        expectedListAfterRotation.add(A);
        expectedListAfterRotation.add(B);

        list = game.getRotatedCardsClockWise(list);

        assertEquals(list, expectedListAfterRotation);
    }

    private List<List<Card>> getMockCards(){
        List<List<Card>> tmp = new ArrayList<>();
        List<Card> tmpA = new ArrayList<>();
        List<Card> tmpB = new ArrayList<>();
        List<Card> tmpC = new ArrayList<>();
        Card mockCard1 = new ResourceActionCard("MockCard1", new ArrayList<>(), ColorEnum.BROWN, new ResourceAction(new ArrayList<>()));
        Card mockCard2 = new EitherResourceCard("MockCard2", new ArrayList<>(), ColorEnum.YELLOW, new EitherResourceAction(new ArrayList<>()));

        tmpA.add(mockCard1);
        tmpB.add(mockCard2);
        tmpC.add(mockCard1);
        tmpC.add(mockCard2);

        tmp.add(tmpA);
        tmp.add(tmpB);
        tmp.add(tmpC);

        return tmp;
    }
}
