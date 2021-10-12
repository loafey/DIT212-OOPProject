package com.ESSBG.app.Model.Cards;

import java.util.List;

import com.ESSBG.app.Model.Action.Handlers.IHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IResourceAction;

public class ResourceActionCard extends Card{

    private IResourceHandler handler;

    /**
     * Constructs a ResourceActionCard
     * @param name
     * @param cost
     * @param color
     * @param handler
     */
    public ResourceActionCard(String name, List<ResourceEnum> cost, ColorEnum color, IResourceHandler handler) {
        super(name, cost, color, CardTypeEnum.RESOURCEACTION);
        this.handler = handler;
    }

    @Override
    public IHandler getHandler() {
        return handler;
    }

}
