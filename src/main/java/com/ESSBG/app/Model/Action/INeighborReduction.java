package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.List;

public interface INeighborReduction<T> {

    int reduceTo();
    List<T> getRightNeighbor();
    List<T> getLeftNeighbor();

}
