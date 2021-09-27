package com.ESSBG.app.Model;

import com.ESSBG.app.Model.Cards.Card;

import java.util.List;

public class PlayerState {

    private List<Card> playedCards;
    private List<Resource> guaranteedResources;
    private List<Resource> eitherResources;
    private List<Resource> leftPlayerReductions;
    private List<Resource> rightPlayerReductions;
    int coins;
    private int warTokens;
    private int winPoints;
    private int losePoints;
    private final Player leftPlayer;
    private final Player rightPlayer;

    public PlayerState(List<Card> playedCards, List<Resource> guaranteedResources, List<Resource> eitherResources, List<Resource> leftPlayerReductions, List<Resource> rightPlayerReductions, int coins, int warTokens, int winPoints, int losePoints, Player leftPlayer, Player rightPlayer) {
        this.playedCards = playedCards;
        this.guaranteedResources = guaranteedResources;
        this.eitherResources = eitherResources;
        this.leftPlayerReductions = leftPlayerReductions;
        this.rightPlayerReductions = rightPlayerReductions;
        this.coins = coins;
        this.warTokens = warTokens;
        this.winPoints = winPoints;
        this.losePoints = losePoints;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
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

    public List<Resource> getGuaranteedResources() {
        return guaranteedResources;
    }

    public List<Resource> getEitherResources() {
        return eitherResources;
    }

    public List<Resource> getLeftPlayerReductions() {
        return leftPlayerReductions;
    }

    public List<Resource> getRightPlayerReductions() {
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

    public void setGuaranteedResources(List<Resource> guaranteedResources) {
        this.guaranteedResources = guaranteedResources;
    }

    public void setEitherResources(List<Resource> eitherResources) {
        this.eitherResources = eitherResources;
    }

    public void setLeftPlayerReductions(List<Resource> leftPlayerReductions) {
        this.leftPlayerReductions = leftPlayerReductions;
    }

    public void setRightPlayerReductions(List<Resource> rightPlayerReductions) {
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


}
