package com.ESSBG.app.Model;

import java.util.List;

public class Game {
    CircularList<Player> players = new CircularList<>();
    List<Card> cardDeck;
    Trashcan trash;
    List<List<Card>> periodCards;



    // Use this method to give war tokens after each age
    private void giveWarTokens(int age){
        int x = 0;

        // Calculate the winning points during each age
        switch (age){
            case 1:
                x = 1;
                break;
            case 2:
                x = 3;
                break;
            case 3:
                x = 5;
                break;
        }

        for (int i=0; i<players.size(); i++){

            Player p = players.get(i);
            Player prev = players.getPrevious(p);
            Player next = players.getNext(p);

            if (p.getWarPoints() < prev.getWarPoints()){
                p.addWarToken(-1);
            }
            if (p.getWarPoints() > prev.getWarPoints()){
                p.addWarToken(x);
            }
            if (p.getWarPoints() < next.getWarPoints()){
                p.addWarToken(-1);
            }
            if (p.getWarPoints() > next.getWarPoints()){
                p.addWarToken(x);
            }
        }
    }

}
