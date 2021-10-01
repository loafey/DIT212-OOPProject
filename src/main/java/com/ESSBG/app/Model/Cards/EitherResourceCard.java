package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;

public class EitherResourceCard extends Card {

    private IEitherAction action;

    public EitherResourceCard(String name, List<ResourceEnum> cost, ColorEnum color) {
        super(name, cost, color);
        
    }
    
    public IEitherAction getAction(){
        return action;
    }

}
