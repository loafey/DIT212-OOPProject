package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public interface INeighborReduction {

    int reduceTo();
    List<ResourceEnum> getRightNeighbor();
    List<ResourceEnum> getLeftNeighbor();

}
