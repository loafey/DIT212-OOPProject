package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Player.Player;

public class MonumentHandler {
    private final Monument monument;
    private final Player player;

    public MonumentHandler(Monument monument){
        this.monument = monument;
        this.player = monument.player;
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
                    //player.setPeacePoints(player.getPeacePoints() + 3);       //points are a guaranteed resource
                    break;
                case 2:
                    monument.stage2Reward();
                    break;
                case 3:
                    //player.setPeacePoints(player.getPeacePoints() + 7);       //points are a guaranteed resource
                default:
                    throw new IllegalStateException("The monument's stage is not between 1-3.");
            }

            //player.discard(card); //player doesn't have discard anymore
            return true;
        }

        return false;
    }


}
