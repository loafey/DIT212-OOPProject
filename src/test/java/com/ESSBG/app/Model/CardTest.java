package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.ICoinAction;
import com.ESSBG.app.Model.Action.EitherResource;
import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Action.INeighborReduction;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ReduceNeighborResources;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.ColorEnum;
import com.ESSBG.app.Model.Player.PlayerState;

public class CardTest {

    @Before
    public void setup(){

        PlayerState state = new PlayerState(3, new Player(null), new Player(null));
        Player player = new Player(null);

        List<ResourceEnum> papyrus = new ArrayList<>();
        papyrus.add(ResourceEnum.PAPYRUS);

        List<ResourceEnum> oreWood = new ArrayList<>();
        oreWood.add(ResourceEnum.ORE);
        oreWood.add(ResourceEnum.WOOD);

        Card<IResourceAction> rCard = new Card<IResourceAction>("Barracks",new ArrayList<>(), ColorEnum.RED, new ResourceAction(papyrus));
        Card<INeighborReduction> nCard = new Card<INeighborReduction>("Barracks",new ArrayList<>(), ColorEnum.RED, new ReduceNeighborResources(new ArrayList<>(), oreWood));;
        Card<IEitherAction> eCard = new Card<IEitherAction>("Barracks",new ArrayList<>(), ColorEnum.RED, new EitherResource(oreWood));;
        Card<ICoinAction> iCard = new Card<ICoinAction>("Barracks",new ArrayList<>(), ColorEnum.RED, new CoinAction(3));;
    }
}
