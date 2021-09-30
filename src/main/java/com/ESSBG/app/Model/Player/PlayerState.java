package com.ESSBG.app.Model.Player;

import com.ESSBG.app.Model.Action.ICoinAction;
import com.ESSBG.app.Model.Action.INeighborReduction;
import com.ESSBG.app.Model.Action.IResourceAction;
import com.ESSBG.app.Model.Cards.Card;
import com.ESSBG.app.Model.ResourceEnum;
import com.ESSBG.app.Model.Action.IEitherAction;

import java.util.ArrayList;
import java.util.List;

public class PlayerState {

    private List<Card<IEitherAction>> playedEitherCards;
    private List<Card<ICoinAction>> playedCoinCards;
    private List<Card<INeighborReduction>> playedNeighborReductionCards;
    private List<Card<IResourceAction>> playedResourceCards;
    private List<ResourceEnum> guaranteedResources;
    private List<ArrayList<ResourceEnum>> eitherResources;
    private List<ResourceEnum> leftPlayerReductions;
    private List<ResourceEnum> rightPlayerReductions;
    int coins;
    private int winPoints;
    private int losePoints;
    private Player leftPlayer;
    private Player rightPlayer;

    public PlayerState(int coins, Player leftPlayer, Player rightPlayer) {
        this.playedEitherCards = new ArrayList<>();
        this.playedCoinCards = new ArrayList<>();
        this.playedNeighborReductionCards = new ArrayList<>();
        this.playedResourceCards = new ArrayList<>();
        this.guaranteedResources = new ArrayList<>();
        this.eitherResources = new ArrayList<>();
        this.leftPlayerReductions = new ArrayList<>();
        this.rightPlayerReductions = new ArrayList<>();
        this.coins = coins;
        this.winPoints = 0;
        this.losePoints = 0;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
    }

    public PlayerState(PlayerState state){
        this.playedEitherCards = state.getPlayedEitherCards();
        this.playedCoinCards = state.getPlayedCoinCards();
        this.playedNeighborReductionCards = state.getPlayedNeighborReductionCards();
        this.playedResourceCards = state.getPlayedResourceCards();
        this.guaranteedResources = state.getGuaranteedResources();
        this.eitherResources = state.getEitherResources();
        this.leftPlayerReductions = state.getLeftPlayerReductions();
        this.rightPlayerReductions = state.getRightPlayerReductions();
        this.coins = state.getCoins();
        this.winPoints = state.getWinPoints();
        this.losePoints = state.getLosePoints();
        this.leftPlayer = state.getLeftPlayer();
        this.rightPlayer = state.getRightPlayer();
    }

    public List<Card<IEitherAction>> getPlayedEitherCards() {
        return playedEitherCards;
    }

    public List<Card<ICoinAction>> getPlayedCoinCards() {
        return playedCoinCards;
    }

    public List<Card<INeighborReduction>> getPlayedNeighborReductionCards() {
        return playedNeighborReductionCards;
    }

    public List<Card<IResourceAction>> getPlayedResourceCards() {
        return playedResourceCards;
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

    public void setPlayedEitherCards(List<Card<IEitherAction>> playedEitherCards) {
        this.playedEitherCards = playedEitherCards;
    }

    public void setPlayedCoinCards(List<Card<ICoinAction>> playedCoinCards) {
        this.playedCoinCards = playedCoinCards;
    }

    public void setPlayedNeighborReductionCards(List<Card<INeighborReduction>> playedNeighborReductionCards) {
        this.playedNeighborReductionCards = playedNeighborReductionCards;
    }

    public void setPlayedResourceCards(List<Card<IResourceAction>> playedResourceCards) {
        this.playedResourceCards = playedResourceCards;
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
