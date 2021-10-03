package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.NeighborReductionAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import org.junit.Before;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class CardTest {

    @Before
    public void setup(){
        Player left = new Player(1, new PlayerState());
        Player main = new Player(2, new PlayerState());
        Player right = new Player(3, new PlayerState());
        left.setLeftNeighbor(right);
        left.setRightNeighbor(main);
        main.setLeftNeighbor(left);
        main.setRightNeighbor(right);
        right.setLeftNeighbor(main);
        right.setRightNeighbor(left);

        ResourceActionCard rac = new ResourceActionCard("Barracks", List.of(ResourceEnum.WOOD), ColorEnum.RED, new ResourceAction(List.of(ResourceEnum.WAR)));
        NeighborReductionCard nrc = new NeighborReductionCard("Marketplace", List.of(), ColorEnum.YELLOW, new NeighborReductionAction(List.of(ResourceEnum.TEXTILE, ResourceEnum.GLASS, ResourceEnum.PAPYRUS)));
        EitherResourceCard erc = new EitherResourceCard("Excavation", List.of(ResourceEnum.COIN), ColorEnum.BROWN, new EitherResourceAction(List.of(ResourceEnum.STONE, ResourceEnum.CLAY)));
    }
}
