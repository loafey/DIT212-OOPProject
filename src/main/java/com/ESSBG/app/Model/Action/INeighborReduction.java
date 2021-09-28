package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public interface INeighborReduction {

    List<ResourceEnum> getLeftNeighborReductions();
    List<ResourceEnum> getRightNeighborReductions();

}
