package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.List;

import com.ESSBG.app.Model.Action.EitherResourceAction;
import com.ESSBG.app.Model.Action.Handlers.EitherHandler;
import com.ESSBG.app.Model.Action.Handlers.IEitherHandler;
import com.ESSBG.app.Model.Action.Handlers.INeighborHandler;
import com.ESSBG.app.Model.Action.Handlers.IResourceHandler;
import com.ESSBG.app.Model.Action.Handlers.NeighborReductionHandler;
import com.ESSBG.app.Model.Action.Handlers.ResourceHandler;
import com.ESSBG.app.Model.Action.ResourceAction;
import com.ESSBG.app.Model.Cards.*;
import com.ESSBG.app.Model.Monument.Monument;
import com.ESSBG.app.Model.Monument.MonumentFactory;
import com.ESSBG.app.Model.Player.InitializePlayers;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Model.Player.PlayerState;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game {
    // TODO change to private
    public CircularList<Player> players = new CircularList<>();
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int handSize = 7;

    public void startGame(ArrayList<Integer> playerIDS) {
        for (Integer pid : playerIDS){
            players.add(new Player(pid, new PlayerState()));
        }
        init();
    }

    /**
     * Puts a player's card into the trash can
     * @param playerIndex
     * @param cardIndex
     */
    public void trashCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        trash.addCard(currentPeriodCards.get(playerIndex).remove(cardIndex));
        PlayerState pState = p.getState();
        pState.addCoins(2);
        p.setState(pState);
    }

    /**
     * Upgrades a player's monument to the next stage, removes a card from their hand and upgrades their playerState,
     * if the player has efficient resources to build the next stage of their monument.
     *
     * @param playerIndex
     * @param cardIndex
     */
    public boolean upgradeMonument(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);
        PlayerState pState = p.getState();
        Monument m = p.getMonument();
        List<ResourceEnum> cost = m.getCostToBuildNextStage();

        if (pState.canAfford(cost)) {
            currentPeriodCards.get(playerIndex).remove(cardIndex);

            List<ResourceEnum> reward = m.getRewardForBuildingNextStage();
            if (m.getName().equals("Alexandria") || m.getName().equals("Babylon")) {
                EitherResourceAction action = new EitherResourceAction(reward);
                EitherHandler handler = new EitherHandler(action);
                handler.updateState(pState);
            } else {
                ResourceAction resourceAction = new ResourceAction(reward);
                ResourceHandler handler = new ResourceHandler(resourceAction);
                handler.updateState(pState);
            }

            m.buildStage();
            return true;
        }
        return false;
    }

    /**
     * Assuming the player has efficient resources to build the structure of card,
     * add the resources given by that card to the player's list of resources
     * @param playerIndex
     * @param cardIndex
     */
    private boolean pickCard(int playerIndex, int cardIndex) {
        Player p = players.get(playerIndex);

        Card c = currentPeriodCards.get(playerIndex).get(cardIndex);

        CardTypeEnum type = c.getCardTypeEnum();
        PlayerState pState = p.getState();
        if (pState.canAfford(c.getCost())) {
            if (type == CardTypeEnum.EITHERRESOURCE) {
                IEitherHandler a = new EitherHandler(((EitherResourceCard) c).getAction());
                pState = a.updateState(pState);
            } else if (type == CardTypeEnum.NEIGHBORREDUCTION) {
                INeighborHandler a = new NeighborReductionHandler(((NeighborReductionCard) c).getAction());
                pState = a.updateState(pState);
            } else if (type == CardTypeEnum.RESOURCEACTION) {
                IResourceHandler a = new ResourceHandler(((ResourceActionCard) c).getAction());
                pState = a.updateState(pState);
            }
            pState.addPlayedCard(c);
            p.setState(pState);
            currentPeriodCards.get(playerIndex).remove(cardIndex);
            return true;
        }
        return false;
    }

    /**
     * Initializes cardDeck, periodCards, age, trashcan and monuments
     */
    public void init() {
        monuments = MonumentFactory.getMonuments();
        players = InitializePlayers.getInitializedPlayers(players, monuments);
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
        trash = new Trashcan();
    }

    /**
     * Moves the game to the next age and changes the cardDeck to the correct cards for that specific age
     */
    private void startNextAge() {
        giveWarTokens(age);
        age++;
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
    }

    /**
     * Gives a player war tokens according to the predefined rules in RAD
     * @param age
     */
    private void giveWarTokens(int age) {
        // Calculate the winning points during each age
        int winPoints = (age * 2) - 1;

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            Player next = players.getNext(p);

            int pWarPoints = p.getState().getWarTokens();
            int nextWarPoints = next.getState().getWarTokens();

            // TODO make immutable
            if (pWarPoints > nextWarPoints) {
                p.getState().addWinPoints(winPoints);
                next.getState().addLosePoints(1);
            } else if (pWarPoints < nextWarPoints) {
                next.getState().addWinPoints(winPoints);
                p.getState().addLosePoints(1);
            }
        }
    }

    public void checkIfNewAgeTime() {
        int deckSize = 0;
        for (List<Card> cards : currentPeriodCards) {
            if (cards.size() > deckSize) {
                deckSize = cards.size();
            }
        }
        if (deckSize < 2) {
            startNextAge();
        }
    }

    private Player calculateWinner() {
        return players.stream().reduce(players.get(0), (p1, p2) -> p1.getState().getTotalScore() > p2.getState().getTotalScore() ? p1 : p2);
    }

    public boolean playerPickCard (int playerIndex, int cardIndex) {
        return pickCard(playerIndex, cardIndex);
    }

    public JSONObject getPlayerData(int playerIndex) {
        Player p = players.get(playerIndex);
        PlayerState pState = p.getState();

        JSONObject data = new JSONObject();
        data.put("msgNum", 0);

        data.put("handCards", new JSONArray());
        // parse hand cards
        for (Card c :  currentPeriodCards.get(playerIndex)) {
            JSONObject cardData = new JSONObject();
            c.getCost().forEach((ResourceEnum r) -> {{
                cardData.append("cost", r.toString());
            }});
            cardData.put("resource", new JSONArray());
            c.getAction().getList().forEach((ResourceEnum r) -> {
                cardData.append("resource", r.toString());
            });

            cardData.put("color", parseColor(c.getColor()));
            
            data.append("handCards", cardData);
        }
        
        data.put("placedCards", new JSONArray());
        // parse placed cards
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(pState.getPlayedCards());
        for (Card c : allCards) {
            JSONObject cardData = new JSONObject();

            cardData.put("color", parseColor(c.getColor()));

            for (ResourceEnum r : c.getCost()){
                cardData.append("cost", r.toString());
            }
            for (ResourceEnum r : c.getAction().getList()){
                cardData.append("resource", r.toString());
            }

            data.append("placedCards", cardData);
        }


        // parse resources
        JSONObject resources = new JSONObject();
        resources.put("war", pState.getWarTokens());
        resources.put("coins", pState.getCoins());
        data.put("resources", resources);

        JSONObject monument = new JSONObject();
        monument.put("unlocked", p.getMonument().getStageBuilt());

        for(ResourceEnum re : p.getMonument().getCostToBuildNextStage()){
            monument.append("upgradeCost", re.toString());
        }

        JSONArray list1 = new JSONArray();
        for(ResourceEnum re : p.getMonument().getStage1Reward()){
            list1.put(re.toString());
        }
        JSONArray list2 = new JSONArray();
        for(ResourceEnum re : p.getMonument().getStage2Reward()){
            list2.put(re.toString());
        }
        JSONArray list3 = new JSONArray();
        for(ResourceEnum re : p.getMonument().getStage3Reward()){
            list3.put(re.toString());
        }
        monument.append("cards", list1);
        monument.append("cards", list2);
        monument.append("cards", list3);

        data.put("monument", monument);

        return data;
    }

    private JSONObject parseColor(ColorEnum color) {
        JSONObject colorData = new JSONObject();
        switch (color) {
            case BLUE:   
                colorData.put("r", 0);
                colorData.put("g", 0);
                colorData.put("b", 1);
                colorData.put("a", 1);
            break;
            case BROWN:  
                colorData.put("r", 150.0/255);
                colorData.put("g", 116.0/255);
                colorData.put("b", 5.0/255);
                colorData.put("a", 1);
            break;
            case GRAY:   
                colorData.put("r", 141.0/255);
                colorData.put("g", 141.0/255);
                colorData.put("b", 141.0/255);
                colorData.put("a", 1);
            break;
            case GREEN:  
                colorData.put("r", 141.0/255);
                colorData.put("g", 141.0/255);
                colorData.put("b", 141.0/255);
                colorData.put("a", 1);
            break;
            case PURPLE: 
                colorData.put("r", 0);
                colorData.put("g", 1);
                colorData.put("b", 0);
                colorData.put("a", 1);
            break;
            case RED:    
                colorData.put("r", 1);
                colorData.put("g", 0);
                colorData.put("b", 0);
                colorData.put("a", 1);
            break;
            case YELLOW: 
                colorData.put("r", 255.0/255);
                colorData.put("g", 187.0/255);
                colorData.put("b", 0);
                colorData.put("a", 1);
            break;
            default: break;
        }
        return colorData;
    }

    /**
     * Takes a list of list of cards and rotates them clockwise
     * @param cards the list of list of cards
     * @return a rotated copy of the list
     */
    public List<List<Card>> getRotatedCardsClockWise(List<List<Card>> cards){
        List<List<Card>> tmp = new ArrayList<>();

        tmp.add(cards.get(cards.size()-1));

        for (int i=0; i<cards.size()-1; i++){
            tmp.add(cards.get(i));
        }

        return tmp;
    }

    /**
     * Takes a list of list of cards and rotates them counterclockwise
     * @param cards the list of list of cards
     * @return a rotated copy of the list
     */
    public List<List<Card>> getRotatedCardsCounterClockWise(List<List<Card>> cards){
        List<List<Card>> tmp = new ArrayList<>();

        for (int i=1; i<cards.size(); i++){
            tmp.add(cards.get(i));
        }

        tmp.add(cards.get(0));

        return tmp;
    }

    /**
     * Rotate all player hands to their neighbors.
     * If the age is an odd number, rotate clockwise.
     * If the age is an even number, rotate counterclockwise.
     */
    public void movePeriodCardsToNextPlayer(){
        if (age % 2 != 0) {
            currentPeriodCards = getRotatedCardsClockWise(currentPeriodCards);
        }
        else {
            currentPeriodCards = getRotatedCardsCounterClockWise(currentPeriodCards);
        }
    }

    public List<List<Card>> getCurrentPeriodCards() {
        return currentPeriodCards;
    }
}
