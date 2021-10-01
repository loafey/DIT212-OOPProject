package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.ReduceNeighborResourceCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.EitherResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerState {

    private List<EitherResourceCard> playedEitherCards;
    private List<ReduceNeighborResourceCard> playedReduceCards;
    private List<ResourceActionCard> playedResourceCards;
    private List<ResourceEnum> guaranteedResources;
    private List<ArrayList<ResourceEnum>> eitherResources;
    private List<ResourceEnum> neighborReductions;
    private int winPoints;
    private int losePoints;


    public PlayerState(int coins, Player leftPlayer, Player rightPlayer) {
        this.playedEitherCards = new ArrayList<>();
        this.playedReduceCards = new ArrayList<>();
        this.playedResourceCards = new ArrayList<>();
        this.guaranteedResources = new ArrayList<>();
        this.eitherResources = new ArrayList<>();
        this.neighborReductions = new ArrayList<>();
        this.winPoints = 0;
        this.losePoints = 0;
    }

    public PlayerState(PlayerState state){
        this.playedEitherCards = state.getPlayedEitherCards();
        this.playedReduceCards = state.getPlayedReduceCards();
        this.playedResourceCards = state.getPlayedResourceCards();
        this.guaranteedResources = state.getGuaranteedResources();
        this.eitherResources = state.getEitherResources();
        this.neighborReductions = state.getNeighborReductions();
        this.winPoints = state.getWinPoints();
        this.losePoints = state.getLosePoints();
    }

    public List<EitherResourceCard> getPlayedEitherCards() {
        return playedEitherCards;
    }

    public List<ReduceNeighborResourceCard> getPlayedReduceCards() {
        return playedReduceCards;
    }

    public List<ResourceActionCard> getPlayedResourceCards() {
        return playedResourceCards;
    }

    public List<ResourceEnum> getGuaranteedResources() {
        return guaranteedResources;
    }

    public List<ArrayList<ResourceEnum>> getEitherResources() {
        return eitherResources;
    }

    public List<ResourceEnum> getNeighborReductions() {
        return neighborReductions;
    }

    public int getCoins() {
        return guaranteedResources.stream()
        .filter(c -> c == ResourceEnum.COIN)
        .collect(Collectors.toList())
        .size();    //save everything that is a coin in a new list and return its length
    }

    public int getWinPoints() {
        return winPoints;
    }

    public int getLosePoints() {
        return losePoints;
    }

    public void setPlayedEitherCards(List<EitherResourceCard> playedEitherCards) {
        this.playedEitherCards = playedEitherCards;
    }

    public void setPlayedReduceCards(List<ReduceNeighborResourceCard> playedReduceCards) {
        this.playedReduceCards = playedReduceCards;
    }

    public void setPlayedResourceCards(List<ResourceActionCard> playedResourceCards) {
        this.playedResourceCards = playedResourceCards;
    }

    public void setGuaranteedResources(List<ResourceEnum> guaranteedResources) {
        this.guaranteedResources = guaranteedResources;
    }

    public void setEitherResources(List<ArrayList<ResourceEnum>> eitherResources) {
        this.eitherResources = eitherResources;
    }

    public void setNeighborReductions(List<ResourceEnum> neighborReductions) {
        this.neighborReductions = neighborReductions;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public void setLosePoints(int losePoints) {
        this.losePoints = losePoints;
    }
    
    /**
     * Returns the amount of war tokens in the guaranteed list.
     * @return
     */
    public int getWarTokens(){
        int count = 0;
        for (ResourceEnum r : guaranteedResources){
            if (r.equals(ResourceEnum.WAR)) count++;
        }
        return count;
    }

    /**
     * Sets the amount of war tokens in the guaranteed list.
     * @param amount
     */
    public void setWarTokens(int amount){
        List<ResourceEnum> resources = guaranteedResources.stream().filter(c -> c != ResourceEnum.WAR).collect(Collectors.toList());
        for (int i = 0; i < amount; i++){
            resources.add(ResourceEnum.WAR);
        }
        setGuaranteedResources(resources);
    }

// ------------------------------------------------------

/**
 * Add a EitherResourceCard to the list of played Either cards.
 * @param c
 */
public void addEitherCard(EitherResourceCard c){
    playedEitherCards.add(c);
}

/**
 * Add a ReduceNeighborResourceCard to the list of played reduce cards
 * @param c
 */
public void addReductionCard(ReduceNeighborResourceCard c){
    playedReduceCards.add(c);
}

/**
 * Add a ResourceCard to the list of played resource acards
 * @param c
 */
public void addResourceCard(ResourceActionCard c){
    playedResourceCards.add(c);
}

/**
 * Adds a resource to the guaranteed resources
 * @param r
 */
public void addGuaranteedResource(ResourceEnum r){
    guaranteedResources.add(r);
}
/**
 * Adds some either resources to the list of eithers
 * @param r
 */
public void addEitherResource(ArrayList<ResourceEnum> r){
    eitherResources.add(r);
}

/**
 * Adds an amount of coins to the guaranteed resources
 * @param amount
 */
public void addCoins(int amount) {
    for (int i = 0; i < amount; i++){
        guaranteedResources.add(ResourceEnum.COIN);
    }
}

/**
 * Adds an amount of points to the win points
 * @param amount
 */
public void addWinPoints(int amount){
    winPoints += amount;
}

/**
 * Adds an amount of points to the lose points
 * @param amount
 */
public void addLosePoints(int amount){
    losePoints += amount;
}


}
