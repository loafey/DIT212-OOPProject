package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerState {
    // TODO change to one list
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
     */
    public PlayerState() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerState that = (PlayerState) o;
        return winPoints == that.winPoints &&
                losePoints == that.losePoints &&
                playedEitherCards.equals(that.playedEitherCards) &&
                playedReductionCards.equals(that.playedReductionCards) &&
                playedResourceCards.equals(that.playedResourceCards) &&
                guaranteedResources.equals(that.guaranteedResources) &&
                eitherResources.equals(that.eitherResources) &&
                neighborReductions.equals(that.neighborReductions);
    }

    /**
     * Getter for playedEitherCards. playedEitherCards represent all played either cards
     * @return List<EitherResourceCard>
     */
    public List<EitherResourceCard> getPlayedEitherCards() {
        return new ArrayList<>(playedEitherCards);
    }

    /**
     * Getter for playedReductionCards. playedReductionCards represent all played reduction cards
     * @return List<NeighborReductionCard>
     */
    public List<NeighborReductionCard> getPlayedReductionCards() {
        return new ArrayList<>(playedReductionCards);
    }

    /**
     * Getter for playdResourceCards. playdResourceCards represent all played resource cards
     * @return List<ResourceActionCard>
     */
    public List<ResourceActionCard> getPlayedResourceCards() {
        return new ArrayList<>(playedResourceCards);
    }

    /**
     * Getter for guaranteedResources. guaranteedResources represent all resources that are guaranteed to be available
     * @return List<ResourceEnum>
     */
    public List<ResourceEnum> getGuaranteedResources() {
        return new ArrayList<>(guaranteedResources);
    }

    /**
     * Getter for eitherResources. eitherResource represent resources which of only one can be chosen from
     * @return List<ArrayList<ResourceEnum>>
     */
    public List<ArrayList<ResourceEnum>> getEitherResources() {
        return new ArrayList<>(eitherResources);     //not a deep copy so pretty useless
    }

    /**
     * Getter for neighborReductions. neighborReductions represent which resources have reduced price
     * @return List<ResourceEnum>
     */
    public List<ResourceEnum> getNeighborReductions() {
        return new ArrayList<>(neighborReductions);
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
     * Getter for winPoints. winPoints represents how many wars were won
     * @return int
     */
    public int getWinPoints() {
        return winPoints;
    }

    /**
     * Getter for losePoints. losePoints represents how many wars were won
     * @return int
     */
    public int getLosePoints() {
        return losePoints;
    }

    /**
     * Setter for playedEitherCards. playedEitherCards represents played either cards
     * @param playedEitherCards
     */
    public void setPlayedEitherCards(List<EitherResourceCard> playedEitherCards) {
        this.playedEitherCards = new ArrayList<>(playedEitherCards);
    }

    /**
     * Setter for playedReductionsCards. playedReductionsCards represents played reduction cards
     * @param playedReduceCards
     */
    public void setPlayedReductionCards(List<NeighborReductionCard> playedReduceCards) {
        this.playedReductionCards = new ArrayList<>(playedReduceCards);
    }

    /**
     * Setter for playedResourceCards
     * @param playedResourceCards
     */
    public void setPlayedResourceCards(List<ResourceActionCard> playedResourceCards) {
        this.playedResourceCards = new ArrayList<>(playedResourceCards);
    }

    /**
     * Setter for guaranteedResources
     * @param newGuaranteedResources
     */
    public void setGuaranteedResources(List<ResourceEnum> newGuaranteedResources) {
        /*List<ResourceEnum> copy = new ArrayList<>(guaranteedResources.size());
        Collections.copy(copy, guaranteedResources);

         */
        this.guaranteedResources = newGuaranteedResources;
    }

    /**
     * Setter for eitherResources
     * @param eitherResources
     */
    public void setEitherResources(List<ArrayList<ResourceEnum>> eitherResources) {
        this.eitherResources = new ArrayList<>(eitherResources);
    }

    /**
     * Setter for neighborReductions
     * @param neighborReductions
     */
    public void setNeighborReductions(List<ResourceEnum> neighborReductions) {
        this.neighborReductions = new ArrayList<>(neighborReductions);
    }

    /**
     * Setter for winPoints. winPoints represents how many wars were won
     * @param winPoints
     */
    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    /**
     * Setter for losePoints. losePoints represents how many wars were lost.
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
