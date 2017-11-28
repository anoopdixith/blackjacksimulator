package dao;

import static impl.Constants.*;
import java.util.*;

/**
 * Created by adixith.
 */
public class Player {
    private long totalMoney;
    private long roundBet;
    private long currentHoldings;
    private long playerId;
    private List<Card> holdingCards;

    public String getPlayerTypeFinancially() {
        return playerTypeFinancially;
    }

    public void setPlayerTypeFinancially(String playerTypeFinancially) {
        this.playerTypeFinancially = playerTypeFinancially;
    }

    public double getPlayerTypeQuitStrategy() {
        return playerTypeQuitStrategy;
    }

    public void setPlayerTypeQuitStrategy(double playerTypeQuitStrategy) {
        this.playerTypeQuitStrategy = playerTypeQuitStrategy;
    }

    public String getPlayerTypePlayStrategy() {
        return playerTypePlayStrategy;
    }

    public void setPlayerTypePlayStrategy(String playerTypePlayStrategy) {
        this.playerTypePlayStrategy = playerTypePlayStrategy;
    }

    private String playerTypeFinancially;
    private double playerTypeQuitStrategy;
    private String playerTypePlayStrategy;

    public String getPlayerTypeBettingStrategy() {
        return playerTypeBettingStrategy;
    }

    public void setPlayerTypeBettingStrategy(String playerTypeBettingStrategy) {
        this.playerTypeBettingStrategy = playerTypeBettingStrategy;
    }

    private String playerTypeBettingStrategy;

    public long getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(long initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    private long initialInvestment;

    public Player(long totalMoney, long playerId) {
        this.totalMoney = totalMoney;
        this.currentHoldings = totalMoney;
        this.playerId = playerId;
        this.holdingCards = new ArrayList<Card>();
        this.initialInvestment = totalMoney;
    }

    public Player(long totalMoney) {
        //long id = House.addAPlayer();
        this(totalMoney, House.addAPlayer());
    }

    public long getRoundBet() {
        return roundBet;
    }


    public long getCurrentHoldings() {
        return currentHoldings;
    }

    public void setCurrentHoldings(long currentHoldings) {
        this.currentHoldings = currentHoldings;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public List<Card> getHoldingCards() {
        return holdingCards;
    }

    public void setHoldingCards(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }

    public void setRoundBet(int roundBet) {
        if (this.totalMoney >= roundBet) {
            this.roundBet = roundBet;
            currentHoldings -= roundBet;
        }
    }

    public void addCard(Card c) {
        this.holdingCards.add(c);
    }

    @Override
    public String toString() {
        String readable = "Player: " + playerId +
                "\nRound bet: " + roundBet +
                "\nHolding:  " + currentHoldings +
                "\nQuitType: " + playerTypeQuitStrategy;
        return readable;
    }

    public long decideRoundBet() {
        long bet = 0;
        switch (playerTypeBettingStrategy) {
            case CONSTANT_BET: bet = currentHoldings <= 0? -1 : 25; break;
            case RANDOM_BET: bet = currentHoldings <= 0? -1 :(long)(Math.random() * currentHoldings); break;
            case BASED_ON_HOLDINGS_BET: {
                bet = currentHoldings <= 0? -1 :
                        (int)(currentHoldings >= (initialInvestment / 24) ? initialInvestment / 24 : currentHoldings);
            }
            break;
            case MARTINGALE_BET: {
                if(currentHoldings >= initialInvestment) {
                    bet = 25;
                }
                else if(currentHoldings < initialInvestment) {
                    bet = currentHoldings <= 0? -1 :
                            (initialInvestment - currentHoldings);
                }
            }
            break;
            default: bet = 25;
        }
        return bet;
    }

}
