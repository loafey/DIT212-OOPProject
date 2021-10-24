package com.ESSBG.app.Model;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ESSBG.app.Network.HashMapWithTypes;
import com.ESSBG.app.Network.ModelNetSerde;
import com.badlogic.gdx.utils.Array;

// import org.json.JSONArray;
// import org.json.JSONObject;

/**
 * Author: Samuel Hammersberg, Sebastian Selander, Emmie Berger
 */
public class Game {
    private final static ModelNetSerde serde = ModelNetSerde.getInstance();
    public CircularList<Player> players = new CircularList<>();
    private List<List<Card>> currentPeriodCards;
    private Trashcan trash;
    private List<Monument> monuments;
    private int age = 1;
    private final int handSize = 7;

    /**
     * "Starts" the game and adds the list of player ids as players.
     *
     * @param playerIDS
     */
    public void startGame(ArrayList<Integer> playerIDS) {
        for (Integer pid : playerIDS) {
            players.add(new Player(pid, new PlayerState()));
        }
        init();
    }

    /**
     * Puts a player's card into the trash can
     *
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
     * Upgrades a player's monument to the next stage, removes a card from their
     * hand and upgrades their playerState, if the player has efficient resources to
     * build the next stage of their monument.
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
     *
     * @param playerIndex
     * @param cardIndex
     */
    public boolean pickCard(int playerIndex, int cardIndex) {
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
     * Moves the game to the next age and changes the cardDeck to the correct cards
     * for that specific age
     */
    private void startNextAge() {
        giveWarTokens(age);
        age++;
        currentPeriodCards = CardFactory.generateHands(age, players.size(), handSize);
    }

    /**
     * Gives a player war tokens according to the predefined rules in RAD
     *
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

    /**
     * Returns the current age
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Check if all decks have a size of 1 or less, and if so proceed to the next
     * set of decks, otherwise do nothing.
     */
    public void tryProgress() {
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

    /**
     * Returns an unsorted list of all player names and their scores.
     *
     * @return
     */
    public HashMapWithTypes getScoreboard() {
        HashMapWithTypes data = new HashMapWithTypes();
        ArrayList<HashMapWithTypes> playerScore = new ArrayList<>();
        players.forEach(p -> {
            HashMapWithTypes score = new HashMapWithTypes();
            score.put("name", p.getName());
            score.put("score", p.getState().getTotalScore());
            playerScore.add(score);
        });
        data.put("scores", playerScore);
        return data;
    }

    /**
     * Returns a JSONObject containing the data of player at said index. Requires
     * the class holding the model to have their own list of players with matching
     * indicies.
     *
     * @param playerIndex
     * @return
     */
    public HashMapWithTypes getPlayerData(int playerIndex) {
        Player p = players.get(playerIndex);
        PlayerState pState = p.getState();

        HashMapWithTypes data = new HashMapWithTypes();
        data.put("msgNum", 0);

        data.put("handCards", new ArrayList<HashMapWithTypes>());

        // parse hand cards
        for (Card c : currentPeriodCards.get(playerIndex)) {
            HashMapWithTypes cardData = new HashMapWithTypes();
            cardData.put("cost", new ArrayList<>());
            c.getCost().forEach((ResourceEnum r) -> {
                {
                    cardData.getStringList("cost").add(r.toString());
                }
            });
            cardData.put("resource", new ArrayList<>());
            c.getAction().getList().forEach((ResourceEnum r) -> {
                cardData.getStringList("resource").add(r.toString());
            });
            cardData.put("cardType", c.getCardTypeEnum());

            cardData.put("color", parseColor(c.getColor()));

            data.getHashMapWithTypesList("handCards").add(cardData);
        }

        data.put("placedCards", new ArrayList<>());
        // parse placed cards
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(pState.getPlayedCards());
        for (Card c : allCards) {
            HashMapWithTypes cardData = new HashMapWithTypes();
            cardData.put("color", parseColor(c.getColor()));

            cardData.put("cost", new ArrayList<>());
            for (ResourceEnum r : c.getCost()) {
                cardData.getStringList("cost").add(r.toString());
            }

            cardData.put("cost", new ArrayList<>());
            for (ResourceEnum r : c.getAction().getList()) {
                cardData.getStringList("resource").add(r.toString());
            }
            cardData.put("cardType", c.getCardTypeEnum().toString());

            data.getHashMapWithTypesList("placedCards").add(cardData);
        }

        // parse resources
        HashMapWithTypes resources = new HashMapWithTypes();
        resources.put("war", pState.getWinPoints());
        resources.put("coins", pState.getCoins());
        data.put("resources", resources);

        HashMapWithTypes monument = new HashMapWithTypes();
        monument.put("unlocked", p.getMonument().getStageBuilt());

        monument.put("upgradeCost", new ArrayList<>());
        for (ResourceEnum re : p.getMonument().getCostToBuildNextStage()) {
            monument.getStringList("upgradeCost").add(re.toString());
        }

        ArrayList<String> list1 = new ArrayList<>();
        for (ResourceEnum re : p.getMonument().getStage1Reward()) {
            list1.add(re.toString());
        }
        ArrayList<String> list2 = new ArrayList<>();
        for (ResourceEnum re : p.getMonument().getStage2Reward()) {
            list2.add(re.toString());
        }
        ArrayList<String> list3 = new ArrayList<>();
        for (ResourceEnum re : p.getMonument().getStage3Reward()) {
            list3.add(re.toString());
        }

        HashMapWithTypes cardMap = new HashMapWithTypes();
        cardMap.put("firstStage", list1);
        cardMap.put("secondStage", list2);
        cardMap.put("thirdStage", list3);

        // TODO - Put overwrites each list...? Changed so all list is in a hashmap
        // instead... Björn, Confirm by remove the todo.
        monument.put("cards", cardMap);

        data.put("monument", monument);

        return data;
    }

    /**
     * Parses a ColorEnum, returning a JSONObject with the rgba fields.
     *
     * @param color
     * @return
     */
    private HashMapWithTypes parseColor(ColorEnum color) {
        HashMapWithTypes colorData = new HashMapWithTypes();
        switch (color) {
        case BLUE:
            colorData.put("r", 0);
            colorData.put("g", 0);
            colorData.put("b", 1);
            colorData.put("a", 1);
            break;
        case BROWN:
            colorData.put("r", 150.0 / 255);
            colorData.put("g", 116.0 / 255);
            colorData.put("b", 5.0 / 255);
            colorData.put("a", 1);
            break;
        case GRAY:
            colorData.put("r", 141.0 / 255);
            colorData.put("g", 141.0 / 255);
            colorData.put("b", 141.0 / 255);
            colorData.put("a", 1);
            break;
        case GREEN:
            colorData.put("r", 141.0 / 255);
            colorData.put("g", 141.0 / 255);
            colorData.put("b", 141.0 / 255);
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
            colorData.put("r", 255.0 / 255);
            colorData.put("g", 187.0 / 255);
            colorData.put("b", 0);
            colorData.put("a", 1);
            break;
        default:
            break;
        }
        return colorData;
    }

    /**
     * Takes a list of list of cards and rotates them clockwise
     *
     * @param cards the list of list of cards
     * @return a rotated copy of the list
     */
    public List<List<Card>> getRotatedCardsClockWise(List<List<Card>> cards) {
        List<List<Card>> tmp = new ArrayList<>();

        tmp.add(cards.get(cards.size() - 1));

        for (int i = 0; i < cards.size() - 1; i++) {
            tmp.add(cards.get(i));
        }

        return tmp;
    }

    /**
     * Takes a list of list of cards and rotates them counterclockwise
     *
     * @param cards the list of list of cards
     * @return a rotated copy of the list
     */
    public List<List<Card>> getRotatedCardsCounterClockWise(List<List<Card>> cards) {
        List<List<Card>> tmp = new ArrayList<>();

        for (int i = 1; i < cards.size(); i++) {
            tmp.add(cards.get(i));
        }

        tmp.add(cards.get(0));

        return tmp;
    }

    /**
     * Rotate all player hands to their neighbors. If the age is an odd number,
     * rotate clockwise. If the age is an even number, rotate counterclockwise.
     */
    public void movePeriodCardsToNextPlayer() {
        if (age % 2 != 0) {
            currentPeriodCards = getRotatedCardsClockWise(currentPeriodCards);
        } else {
            currentPeriodCards = getRotatedCardsCounterClockWise(currentPeriodCards);
        }
    }

    public List<List<Card>> getCurrentPeriodCards() {
        return currentPeriodCards;
    }
}
