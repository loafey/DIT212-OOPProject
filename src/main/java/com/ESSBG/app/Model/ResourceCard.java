package com.ESSBG.app.Model;

import java.util.List;
import java.util.Map;

public class ResourceCard extends AbstractCard {

    private List<String> type;

    public ResourceCard(Map<String, Integer> cost, ColorEnum color, List<String> type) {
        super(cost, color);
        this.type = type;
    }

    public List<String> getType() {
        return type;
    }
}
