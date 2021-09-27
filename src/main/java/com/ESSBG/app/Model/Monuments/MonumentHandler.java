package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Player;
import com.ESSBG.app.Model.Resource;

import java.util.List;

public class MonumentHandler {
    private final Player player;
    private final Monument monument;

    public MonumentHandler(Player player){
        this.player = player;
        monument = player.getMonument();
    }

    /**
     * We assume the player has a card on their hand to discard.
     * This method checks if a player has enough resources to build the next stage of their monument.
     * If they have enough, the player will be given the rewards for the specific stage of the wonder built,
     * and also discard one of their cards.
     * If they don't have efficient resources, the monument's and the player's attributes
     * will remain unchanged.
     * @return
     */

    public boolean buildStageOfMonument(Card card){
        boolean playerHasEfficientResources = false;    // Vet ej hur man ska kolla det

        if (playerHasEfficientResources && monument.getStageBuilt() < 3){
            monument.buildStage();

            switch (monument.getStageBuilt()){
                case 1:
                    player.setPeacePoints(player.getPeacePoints() + 3);
                    break;
                case 2:
                    // olika fall, vet ej hur man löser
                    break;
                case 3:
                    player.setPeacePoints(player.getPeacePoints() + 7);
                default:
                    throw new IllegalStateException("The monument's stage is not between 1-3.");
            }

            player.discard(card);
            return true;
        }

        return false;
    }

    /**
     * Adds the starting resource given by their monument to a player
     * if their list of resources is empty. Otherwise throw an exception.
     */
    public void givePlayerStartingResource(){
        List<Resource> list = player.getGuaranteedResources();

        if (list.isEmpty()) {
            list.add(monument.getStartingResource());
            player.setGuaranteedResources(list);
        }
        else{
            throw new IllegalStateException("The player's resources should be empty when they are given their starting resource");
        }
    }
}
