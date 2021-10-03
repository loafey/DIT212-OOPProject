package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Monument.Monuments.Alexandria;
import com.ESSBG.app.Model.Monument.Monument;

import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        List <ResourceEnum> generation = new ArrayList<>();
        generation.add(ResourceEnum.WOOD);
        List<IResourceAction> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(ResourceEnum.ORE)));
        Monument mon = new Alexandria();
        //player = new Player();
    }
}