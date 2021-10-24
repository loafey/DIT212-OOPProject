package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.Monument.Monuments.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Author: Emmie Berger
 *
 * A factory class for Monument.
 * Produces a list of each type of monument available in the game (one object of each subclass to Monument)
 */


public class MonumentFactory {
    private List<Monument> monuments = new ArrayList<>();

    private MonumentFactory() {
        initMonumentList();
        Random r = new Random(getSeed());
        Collections.shuffle(monuments,r);
    }

    /**
     * Generates an int based on today's date to be used for generating random numbers
     * @return an integer based on today's date
     */
    private int getSeed() {
        LocalDate d = java.time.LocalDate.now();
        int seed = 0;
        seed += d.getYear();
        seed += d.getMonthValue();
        seed += d.getDayOfMonth();
        return seed;
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
     * @return a shuffled list of all monuments
     */
    public static List<Monument> getMonuments() {
        MonumentFactory m = new MonumentFactory();
        return m.monuments;
    }


}
