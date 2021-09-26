package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.List;

public interface INeighborAction extends IAction {

    boolean rightNeighbor();
    boolean leftNeighbor();
    List<Resource> resources();

}
