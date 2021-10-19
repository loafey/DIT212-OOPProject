package com.ESSBG.app.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleableList <T> extends ArrayList<T> {
    ArrayList<T> list;

    public ShuffleableList(ArrayList<T> list){
        this.list = list;
    }


    /**
     * Generates an int based on today's date to be sued for generating random numbers
     * @return
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
     * Shuffles a list using Fisher yates method
     *
     */

    public void shuffle() {
        Random random = new Random(getSeed());
        int size = list.size();

        for (int i = size - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            T tmp1 = list.get(i);
            T tmp2 = list.get(j);
            list.set(i, tmp2);
            list.set(j, tmp1);
        }
    }

    public ArrayList<T> getList() {
        return list;
    }
}
