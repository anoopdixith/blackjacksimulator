package dao;

import java.util.*;

/**
 * Created by adixith
 */
public class Table {
    private BlackJackEntity blackJackEntity;
    private int numberOfPlayers;
    private List<Player> allPlayers;
    private CardState cardState;

    public Table(BlackJackEntity blackJackEntity,
                 List<Player> allPlayers,
                 CardState cardState) {

        this.blackJackEntity = blackJackEntity;
        this.numberOfPlayers = allPlayers.size();
        this.cardState = cardState;
    }

    public Table(BlackJackEntity blackJackEntity) {
        this(blackJackEntity, new ArrayList<Player>(),
                new CardState(blackJackEntity));
    }

    public BlackJackEntity getBlackJackEntity() {
        return blackJackEntity;
    }

    public void setBlackJackEntity(BlackJackEntity blackJackEntity) {
        this.blackJackEntity = blackJackEntity;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }
}
