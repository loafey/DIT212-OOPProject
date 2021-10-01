package com.ESSBG.app.Model.Monument;

import com.ESSBG.app.Model.Monument.Monuments.*;
import com.ESSBG.app.Model.Player.Player;

import java.util.*;

public class MonumentFactory {
    private List<Monument> monuments = new ArrayList<>();
    private List<String> tmpList = new LinkedList<>();

    private MonumentFactory(List<Player> players){
        initTmpList();
        shuffle(tmpList);
        assignPlayersToMonuments(players);
    }

    /**
     * Uses tmpList to assign each player to a monument if tmpList has been initialized
     * @param players
     */
    private void assignPlayersToMonuments(List<Player> players){
        if (!tmpList.isEmpty()) {
            for (int i = 0; i < players.size(); i++) {
                String s = tmpList.get(i);
                Player p = players.get(i);

                switch (s) {
                    case "A":
                        monuments.add(new Alexandria(p));
                        break;
                    case "B":
                        monuments.add(new Babylon(p));
                        break;
                    case "E":
                        monuments.add(new Ephesos(p));
                        break;
                    case "G":
                        monuments.add(new Gizah(p));
                        break;
                    case "H":
                        monuments.add(new Halikarnassos(p));
                        break;
                    case "O":
                        monuments.add(new Olympia(p));
                        break;
                    case "R":
                        monuments.add(new Rhodos(p));
                        break;
                    default:
                        throw new IllegalStateException("Assigning players to monuments went wrong");
                }
            }
        }
        else {
            throw new IllegalStateException("tmpList hasn't been initialized correctly, it is empty");
        }
    }

    /**
     * Adds a letter representing each possible monument to tmpList if tmpList is empty
      */

    private void initTmpList(){
        if (tmpList.isEmpty()) {
            tmpList.add("A");
            tmpList.add("B");
            tmpList.add("E");
            tmpList.add("G");
            tmpList.add("H");
            tmpList.add("O");
            tmpList.add("R");
        }
        else {
            throw new IllegalStateException("tmpList must be empty to initialized");
        }
    }

    /**
     * Shuffles a list using Fisher yates method
     * @param list
     * @param <T>
     */
    private <T> void shuffle (List<T> list){
        Random random = new Random();
        int size = list.size();

        for (int i = size-1; i > 0; i--) {

            int j = random.nextInt(i+1);

            T tmp1 = list.get(i);
            T tmp2 = list.get(j);
            list.set(i, tmp2);
            list.set(j, tmp1);
        }
    }


    public static List<Monument> getMonuments(List<Player> players){
        MonumentFactory m = new MonumentFactory(players);
        return m.monuments;
    }






}
