package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Action.IEitherAction;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Monuments.Alexandria;
import com.ESSBG.app.Model.Monuments.Monument;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        List <ResourceEnum> generation = new ArrayList<>();
        generation.add(ResourceEnum.WOOD);
        List<IResourceAction> monAction = new ArrayList<>();
        monAction.add(new ResourceAction(List.of(ResourceEnum.ORE)));
        Monument mon = new Alexandria(player);
        player = new Player(0, new PlayerState(null));
    }
}