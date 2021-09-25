package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.Resource;

import java.util.List;

public interface INeighboraction {

    boolean rightNeighbor();
    boolean leftNeighbor();
    List<Resource> resources();

}
