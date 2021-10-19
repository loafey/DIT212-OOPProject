package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.Monument.Monuments.*;
import com.ESSBG.app.Model.ShuffleableList;

import java.time.LocalDate;
import java.util.*;

/**
 * Author: Emmie Berger
 * <p>
 * A factory class for Monument.
 * Produces a list of each type of monument available in the game (one object of each subclass to Monument)
 */

public class MonumentFactory {
    private ArrayList<Monument> monuments = new ArrayList<>();


    private MonumentFactory() {
        initMonumentList();
        ShuffleableList shuffleableList = new ShuffleableList(monuments);
        shuffleableList.shuffle();
        monuments = shuffleableList.getList();
    }


    /**
     * Add all possible monuments to the monument list
     */
    private void initMonumentList() {
        monuments.add(new Alexandria());
        monuments.add(new Babylon());
        monuments.add(new Rhodos());
        monuments.add(new Olympia());
        monuments.add(new Halikarnassos());
        monuments.add(new Gizah());
        monuments.add(new Ephesus());


    }

    /**
     * Gives a shuffled list of all monuments (one of each kind)
     *
     * @return
     */

    public static List<Monument> getMonuments() {
        MonumentFactory m = new MonumentFactory();
        return m.monuments;
    }


}
