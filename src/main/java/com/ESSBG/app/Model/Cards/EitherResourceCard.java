package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;

/**
 * Author: Sebastian Selander
 * 
 * A concrete card that holds an either action.
 */
public class EitherResourceCard extends Card {

    private IEitherAction action;

    /**
     * Constructor for EitherResourceCard
     * @param name
     * @param cost
     * @param color
     */
    public EitherResourceCard(String name, List<ResourceEnum> cost, ColorEnum color, IEitherAction action) {
        super(name, cost, color, CardTypeEnum.EITHERRESOURCE);
        this.action = action;
    }
    
    /**
     * Getter for action
     * @return IEitherAction
     */
    public IEitherAction getAction(){
        return action;
    }

}
