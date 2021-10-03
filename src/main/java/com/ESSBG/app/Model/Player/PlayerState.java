package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerState {

    private List<EitherResourceCard> playedEitherCards;
    private List<NeighborReductionCard> playedReductionCards;
    private List<ResourceActionCard> playedResourceCards;
    private List<ResourceEnum> guaranteedResources;
    private List<ArrayList<ResourceEnum>> eitherResources;
    private List<ResourceEnum> neighborReductions;
    private int winPoints;
    private int losePoints;

    /**
     * Constructor for a PlayerState
     * @param leftPlayer
     * @param rightPlayer
     */
    public PlayerState(Player leftPlayer, Player rightPlayer) {
        this.playedEitherCards = new ArrayList<>();
        this.playedReductionCards = new ArrayList<>();
        this.playedResourceCards = new ArrayList<>();
        this.guaranteedResources = new ArrayList<>();
        this.eitherResources = new ArrayList<>();
        this.neighborReductions = new ArrayList<>();
        this.winPoints = 0;
        this.losePoints = 0;
    }

    /**
     * Constructor for a PlayerState. Copies the given PlayerState.
     * @param state
     */
    public PlayerState(PlayerState state){
        this.playedEitherCards = state.getPlayedEitherCards();
        this.playedReductionCards = state.getPlayedReductionCards();
        this.playedResourceCards = state.getPlayedResourceCards();
        this.guaranteedResources = state.getGuaranteedResources();
        this.eitherResources = state.getEitherResources();
        this.neighborReductions = state.getNeighborReductions();
        this.winPoints = state.getWinPoints();
        this.losePoints = state.getLosePoints();
    }

    /**
     * Getter for playedEitherCards
     * @return List<EitherResourceCard>
     */
    public List<EitherResourceCard> getPlayedEitherCards() {
        List<EitherResourceCard> copy = new ArrayList<>();
        Collections.copy(copy, playedEitherCards);
        return copy;
    }

    /**
     * Getter for playedReductionCards
     * @return List<NeighborReductionCard>
     */
    public List<NeighborReductionCard> getPlayedReductionCards() {
        List<NeighborReductionCard> copy = new ArrayList<>();
        Collections.copy(copy, playedReductionCards);
        return copy;
    }

    /**
     * Getter for playdResourceCards
     * @return List<ResourceActionCard>
     */
    public List<ResourceActionCard> getPlayedResourceCards() {
        List<ResourceActionCard> copy = new ArrayList<>();
        Collections.copy(copy, playedResourceCards);
        return copy;
    }

    /**
     * Getter for guaranteedResources
     * @return List<ResourceEnum>
     */
    public List<ResourceEnum> getGuaranteedResources() {
        List<ResourceEnum> copy = new ArrayList<>();
        Collections.copy(copy, guaranteedResources);
        return copy;
    }

    /**
     * Getter for eitherResources
     * @return List<ArrayList<ResourceEnum>>
     */
    public List<ArrayList<ResourceEnum>> getEitherResources() {
        List<ArrayList<ResourceEnum>> copy = new ArrayList<>();     //not a deep copy so pretty useless
        Collections.copy(copy, eitherResources);        
        return copy;
    }

    /**
     * Getter for neighborReductions
     * @return List<ResourceEnum>
     */
    public List<ResourceEnum> getNeighborReductions() {
        List<ResourceEnum> copy = new ArrayList<>();
        Collections.copy(copy, neighborReductions);
        return copy;
    }

    /**
     * Return the amount of coins in guaranteedResources
     * @return int
     */
    public int getCoins() {
        return guaranteedResources.stream()
        .filter(c -> c == ResourceEnum.COIN)
        .collect(Collectors.toList())
        .size();    //save everything that is a coin in a new list and return its length
    }

    /**
     * Getter for winPoints
     * @return int
     */
    public int getWinPoints() {
        return winPoints;
    }

    /**
     * Getter for losePoints
     * @return int
     */
    public int getLosePoints() {
        return losePoints;
    }

    /**
     * Setter for playedEitherCards
     * @param playedEitherCards
     */
    public void setPlayedEitherCards(List<EitherResourceCard> playedEitherCards) {
        List<EitherResourceCard> copy = new ArrayList<>();
        Collections.copy(copy, playedEitherCards);
        this.playedEitherCards = copy;
    }

    /**
     * Setter for playedReductionsCards
     * @param playedReduceCards
     */
    public void setPlayedReductionCards(List<NeighborReductionCard> playedReduceCards) {
        List<NeighborReductionCard> copy = new ArrayList<>();
        Collections.copy(copy, playedReduceCards);
        this.playedReductionCards = copy;
    }

    /**
     * Setter for playedResourceCards
     * @param playedResourceCards
     */
    public void setPlayedResourceCards(List<ResourceActionCard> playedResourceCards) {
        List<ResourceActionCard> copy = new ArrayList<>();
        Collections.copy(copy, playedResourceCards);
        this.playedResourceCards = copy;
    }

    /**
     * Setter for guaranteedResources
     * @param guaranteedResources
     */
    public void setGuaranteedResources(List<ResourceEnum> guaranteedResources) {
        List<ResourceEnum> copy = new ArrayList<>();
        Collections.copy(copy, guaranteedResources);
        this.guaranteedResources = copy;
    }

    /**
     * Setter for eitherResources
     * @param eitherResources
     */
    public void setEitherResources(List<ArrayList<ResourceEnum>> eitherResources) {
        List<ArrayList<ResourceEnum>> copy = new ArrayList<>();
        Collections.copy(copy, eitherResources);
        this.eitherResources = copy;
    }

    /**
     * Setter for neighborReductions
     * @param neighborReductions
     */
    public void setNeighborReductions(List<ResourceEnum> neighborReductions) {
        List<ResourceEnum> copy = new ArrayList<>();
        Collections.copy(copy, neighborReductions);
        this.neighborReductions = copy;
    }

    /**
     * Setter for winPoints
     * @param winPoints
     */
    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    /**
     * Setter for losePoints
     * @param losePoints
     */
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
public void addReductionCard(NeighborReductionCard c){
    playedReductionCards.add(c);
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
