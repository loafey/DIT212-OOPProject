package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.Cards.EitherResourceCard;
import com.ESSBG.app.Model.Cards.NeighborReductionCard;
import com.ESSBG.app.Model.Cards.ResourceActionCard;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ESSBG.app.Model.ResourceEnum.*;

/**
 * @author Sebastian Selander, Samuel Hammersberg, Emmie Berger
 * 
 * A class that holds a state of a player. Everything that can change for a player during the course of a game should be here. 
 */
public class PlayerState {
    private List<Card> playedCards; //the type of the card doesn't matter when it has been played
    private List<ResourceEnum> guaranteedResources;
    private List<ArrayList<ResourceEnum>> eitherResources;
    private List<ResourceEnum> neighborReductions;
    private int winPoints;
    private int losePoints;


    /**
     * Constructor for a PlayerState
     */
    public PlayerState() {
        this.playedCards = new ArrayList<>();
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
        this.playedCards = state.getPlayedCards();
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
                playedCards.equals(that.playedCards) &&
                guaranteedResources.equals(that.guaranteedResources) &&
                eitherResources.equals(that.eitherResources) &&
                neighborReductions.equals(that.neighborReductions);
    }

    /**
     * Getter for playedCards
     * @return List<Card>
     */
    public List<Card> getPlayedCards(){
        return new ArrayList<>(playedCards);
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
        .size(); //save everything that is a coin in a new list and return its length
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
     * Setter for playedCards
     * @param playedCards
     */
    public void setPlayedCards(List<EitherResourceCard> playedCards) {
        this.playedCards = new ArrayList<>(playedCards);
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
     * Add a Card to the list of played cards.
     * @param c
     */
    public void addPlayedCard(Card c){
        playedCards.add(c);
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
            guaranteedResources.add(COIN);
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

    /**
     * Check if a player can the cost of the argument
     * @param cost
     * @return true if player can afford, else false
     */
    public boolean canAfford(List<ResourceEnum> cost){
        boolean richEnough = true;
        cost = new ArrayList<>(cost);
        List<ResourceEnum> guaranteed = getGuaranteedResources();
        List<ArrayList<ResourceEnum>> eithers = getEitherResources(); 
        for (int i = 0; i < cost.size(); i++){
            ResourceEnum r = cost.get(i);
            if(guaranteed.contains(r)) {
                guaranteed.remove(r);
                cost.remove(r);
                i--;
            } else {
                richEnough = false;
                break;
            }
        }
        if (richEnough) {
            return true;
        } else {
            for (int ri = 0; ri < cost.size(); ri++){
                ResourceEnum r = cost.get(ri);
                for (int i = 0; i < eithers.size(); i++){
                    if (eithers.get(i).contains(r)) {
                        eithers.remove(i);
                        i--;
                        cost.remove(r);
                        ri--;
                    }
                }
            } // currently eitherResources have no extra functionality compared to guaranteedResources.    
        }     // TODO can be solved using satisfiability. Kind of out of scope in this course and low on time.
        return cost.size() == 0;
    }

    /**
     * Returns the total score for this player state
     * @return
     */
    public int getTotalScore(){
        int pPoints = getNrOfResources(POINT);
        int pCoins = getNrOfResources(COIN) / 3;
        int pWarPoints = winPoints - losePoints;
        int pGreenPoints = getPointsFromGreenCards();

        return pPoints + pCoins + pWarPoints + pGreenPoints;
    }

    /**
     * Calculates the points earned from green cards (library, dispensary, laboratory) for this player state
     * @return
     */
    public int getPointsFromGreenCards(){
        int squaredPoints = getNrOfResources(Library) * getNrOfResources(Library) + getNrOfResources(Dispensary) * getNrOfResources(Dispensary) + getNrOfResources(Laboratory) * getNrOfResources(Laboratory);
        int groupPoints = Math.min(getNrOfResources(Laboratory), (Math.min(getNrOfResources(Library), getNrOfResources(Dispensary))));

        return squaredPoints + groupPoints;
    }

    /**
     * Gives the amount of a specified resource in this playerstate's list of guaranteed resources
     * @param r the resource to calculate the amount for
     * @return the amount of the resource
     */

    public int getNrOfResources(ResourceEnum r){
        int n = 0;

        for (ResourceEnum resource : guaranteedResources){
            if (resource.equals(r)){
                n++;
            }
        }

        return n;
    }
}