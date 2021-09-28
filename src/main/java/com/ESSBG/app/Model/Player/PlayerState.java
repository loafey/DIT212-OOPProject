package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.ResourceEnum;

import java.util.ArrayList;
import java.util.List;

public class PlayerState {

    private List<Card> playedCards;
    private List<ResourceEnum> guaranteedResources;
    private List<ArrayList<ResourceEnum>> eitherResources;
    private List<ResourceEnum> leftPlayerReductions;
    private List<ResourceEnum> rightPlayerReductions;
    int coins;
    private int warTokens;
    private int winPoints;
    private int losePoints;
    private Player leftPlayer;
    private Player rightPlayer;

    public PlayerState(List<Card> playedCards, List<ResourceEnum> guaranteedResources, List<ArrayList<ResourceEnum>> eitherResources, List<ResourceEnum> leftPlayerReductions, List<ResourceEnum> rightPlayerReductions, int coins, int warTokens, int winPoints, int losePoints) {
        this.playedCards = playedCards;
        this.guaranteedResources = guaranteedResources;
        this.eitherResources = eitherResources;
        this.leftPlayerReductions = leftPlayerReductions;
        this.rightPlayerReductions = rightPlayerReductions;
        this.coins = coins;
        this.warTokens = warTokens;
        this.winPoints = winPoints;
        this.losePoints = losePoints;
    }

    public PlayerState(PlayerState state){
        this.playedCards = state.getPlayedCards();
        this.guaranteedResources = state.getGuaranteedResources();
        this.eitherResources = state.getEitherResources();
        this.leftPlayerReductions = state.getLeftPlayerReductions();
        this.rightPlayerReductions = state.getRightPlayerReductions();
        this.coins = state.getCoins();
        this.warTokens = state.getWarTokens();
        this.winPoints = state.getWinPoints();
        this.losePoints = state.getLosePoints();
        this.leftPlayer = state.getLeftPlayer();
        this.rightPlayer = state.getRightPlayer();
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public List<ResourceEnum> getGuaranteedResources() {
        return guaranteedResources;
    }

    public List<ArrayList<ResourceEnum>> getEitherResources() {
        return eitherResources;
    }

    public List<ResourceEnum> getLeftPlayerReductions() {
        return leftPlayerReductions;
    }

    public List<ResourceEnum> getRightPlayerReductions() {
        return rightPlayerReductions;
    }

    public int getCoins() {
        return coins;
    }

    public int getWarTokens() {
        return warTokens;
    }

    public int getWinPoints() {
        return winPoints;
    }

    public int getLosePoints() {
        return losePoints;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public PlayerState getleftPlayerState(){
        return leftPlayer.getState();
    }

    public PlayerState getRightPlayerState(){
        return rightPlayer.getState();
    }

    public void setPlayedCards(List<Card> playedCards) {
        this.playedCards = playedCards;
    }

    public void setGuaranteedResources(List<ResourceEnum> guaranteedResources) {
        this.guaranteedResources = guaranteedResources;
    }

    public void setEitherResources(List<ArrayList<ResourceEnum>> eitherResources) {
        this.eitherResources = eitherResources;
    }

    public void setLeftPlayerReductions(List<ResourceEnum> leftPlayerReductions) {
        this.leftPlayerReductions = leftPlayerReductions;
    }

    public void setRightPlayerReductions(List<ResourceEnum> rightPlayerReductions) {
        this.rightPlayerReductions = rightPlayerReductions;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setWarTokens(int warTokens) {
        this.warTokens = warTokens;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public void setLosePoints(int losePoints) {
        this.losePoints = losePoints;
    }

    public void setLeftPlayer(Player leftPlayer){
        this.leftPlayer = leftPlayer;
    }

    public void setRightPlayer(Player leftPlayer){
        this.leftPlayer = leftPlayer;
    }

}
