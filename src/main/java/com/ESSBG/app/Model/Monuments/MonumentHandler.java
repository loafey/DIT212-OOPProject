package com.ESSBG.app.Model.Monuments;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

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
                    level1Reward();      //points are a guaranteed resource
                    break;
                case 2:
                    monument.stage2Reward();
                    break;
                case 3:
                    level3Reward();      //points are a guaranteed resource
                default:
                    throw new IllegalStateException("The monument's stage is not between 1-3.");
            }

            //player.discard(card); //player doesn't have discard anymore
            return true;
        }

        return false;
    }

    /* Anledningen till att level1Reward() och level3Reward() är här och inte i Monument
       är för att minska risken för fel eftersom de bara ska kallas i den här klassen

     */

    private void level1Reward(){
        if (monument.getStageBuilt() == 1) {
            List<ResourceEnum> list = player.getState().getGuaranteedResources(); // Vet att det bryter mot law of demeter, behöver fixa
            for (int i = 0; i < 3; i++) {
                list.add(ResourceEnum.POINT);
            }
            player.getState().setGuaranteedResources(list);
        }
        else {
            throw new IllegalStateException("Cannot give the player a level 1-reward unless they're at level 1");
        }
    }

    private void level3Reward(){
        if (monument.getStageBuilt() == 3) {
            List<ResourceEnum> list = player.getState().getGuaranteedResources();
            for (int i = 0; i < 7; i++) {
                list.add(ResourceEnum.POINT);
            }
            player.getState().setGuaranteedResources(list);
        }
        else {
            throw new IllegalStateException("Cannot give the player a level 3-reward unless they're at level 3");
        }
    }


}
