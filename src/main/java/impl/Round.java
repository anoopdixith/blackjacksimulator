package impl;

import dao.*;
import java.util.*;

/**
 * Created by adixith.
 */
public class Round {
    private List<Player> playersForThisRound;
    private Table currentTable;
    private int roundNumber;
    Card dealerCardUp = null;
    private boolean isComplete;

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Card getDealerCardUp() {
        return dealerCardUp;
    }

    public void setDealerCardUp(Card dealerCardUp) {
        this.dealerCardUp = dealerCardUp;
    }

    public Round(List<Player> playersForThisRound, Table currentTable) {
        this.playersForThisRound = playersForThisRound;
        this.currentTable = currentTable;
        this.roundNumber = 1;
        this.isComplete = false;
    }

    public List<Player> getPlayersForThisRound() {
        return playersForThisRound;
    }

    public void setPlayersForThisRound(List<Player> playersForThisRound) {
        this.playersForThisRound = playersForThisRound;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }

    public int getRountNumber() {
        return roundNumber;
    }

    public void setRountNumber(int rountNumber) {
        this.roundNumber = rountNumber;
    }

    public void clearCards() {
        for(Player p:playersForThisRound) {
            p.getHoldingCards().clear();
        }
    }
}
