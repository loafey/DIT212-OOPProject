package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.ResourceEnum;

public class EitherResourceCard extends Card {

    private IEitherHandler handler;

    /**
     * Constructor for EitherResourceCard
     * @param name
     * @param cost
     * @param color
     * @param handler
     */
    public EitherResourceCard(String name, List<ResourceEnum> cost, ColorEnum color, IEitherHandler handler) {
        super(name, cost, color, CardTypeEnum.EITHERRESOURCE);
        this.handler = handler;
    }
    


}
