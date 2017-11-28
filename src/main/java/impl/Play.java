package impl;

import dao.*;
import static impl.Constants.*;
import java.util.*;

/**
 * Created by adixith.
 */
public class Play {
    private static final String PRESET = "";
    Player housePlayer;
    static int numberOfProfitInstances = 0;
    static int numberOfBankruptcies = 0;
    static List<Integer> decisiveGameNumberForProfitQuit = new ArrayList<>();
    static List<Integer> decisiveGameNumberForBankruptcyQuit = new ArrayList<>();

    public static void main(String a[]) {
        int visits = 100;
        for(int v=0; v < visits; v++) {
            new Play().initPlay();
        }

        System.out.println("Bankruptcies: " + numberOfBankruptcies);
        System.out.println("Profits: " + numberOfProfitInstances);
        double ratio = numberOfProfitInstances * 1.0 /numberOfBankruptcies;
        System.out.println("Profits/Bankruptcies ratio: " + ratio);

        double avg = 0.0;
        for(Integer i:decisiveGameNumberForProfitQuit) {
            avg += i;
        }
        avg /= decisiveGameNumberForProfitQuit.size();
        System.out.println("Profit quit average game number: " + avg);

    }

    public void initPlay() {
        int numberOfPlayers = 0;
        int numberOfGames = 1000;
        housePlayer = House.getInstance().getHousePlayer();
        BlackJackEntity blackJackEntity = new BlackJackEntity();
        Table table = new Table(blackJackEntity);
        List<Player> playersForThisTable = Utils.getPlayers(numberOfPlayers);
        // Simulate myself
        Player anoop = new Player(1000);
        anoop.setPlayerTypeQuitStrategy(1.5);
        anoop.setPlayerTypeBettingStrategy(MARTINGALE_BET);
        playersForThisTable.add(anoop);

        /*
        for(Player p:playersForThisTable) {
            printStats(p);
        }

        printStats(housePlayer);
        */

        Round round = new Round(playersForThisTable, table);
        for(int gameNum = 1; gameNum < numberOfGames; gameNum++) {
            startPlay(round, gameNum);
            round.clearCards();
            housePlayer.getHoldingCards().clear();
            if(round.getPlayersForThisRound().size() == 0) {
                return;
            }
        }

        /*
        for(Player p:playersForThisTable) {
            printStats(p);
        }


        printStats(housePlayer);
        */

    }

    public void startPlay(Round round, int gameNumber) {
        int roundNumber = 1;
        Iterator<Player> iterator = round.getPlayersForThisRound().iterator();
        while (iterator.hasNext()) {
            Player p = iterator.next();
            if(p.getPlayerId() >= 10000) {
                int bet = (int) p.decideRoundBet();
                if (bet <= 0) {
                    iterator.remove();
                    numberOfBankruptcies++;
                    System.out.println("Player " + p.getPlayerId() + " is REMOVED BECAUSE OF BANKRUPTCY in game number " + gameNumber);
                    decisiveGameNumberForBankruptcyQuit.add(Integer.valueOf(gameNumber));
                }
                else if(p.getCurrentHoldings() > ( p.getInitialInvestment() * p.getPlayerTypeQuitStrategy())) {
                    iterator.remove();
                    numberOfProfitInstances++;
                    decisiveGameNumberForProfitQuit.add(Integer.valueOf(gameNumber));
                    System.out.println("Player " + p.getPlayerId() + " QUITTING WITH PROFIT OF " + p.getPlayerTypeQuitStrategy()
                    + " Initial investment: " + p.getInitialInvestment() + " Quit at: " + p.getCurrentHoldings()
                    + " in game number " + gameNumber);

                }
                else {
                    p.setRoundBet(bet);
                }
            }
        }
        while(roundNumber++ <= 2) {
            round = executeRound(round);
        }

        round = examineRound(round);
        // printStats(round);
    }

    private void printStats(Round round) {
        System.out.println();
        for(Player p: round.getPlayersForThisRound()) {
            System.out.println(p.toString());
            System.out.println("Total " + runningTotal(p));
            System.out.println();
        }

        System.out.println(housePlayer.toString());
        System.out.println("Total " + runningTotal(housePlayer));
        System.out.println();
    }

    private void printStats(Player player) {
        System.out.println();
        System.out.println(player.toString());
        System.out.println();
    }

    private Round examineRound(Round round) {
        /*
        for(Player p: round.getPlayersForThisRound()) {
            Card c1 = p.getHoldingCards().get(0);
            Card c2 = p.getHoldingCards().get(1);
            int runningTotal = c1.getValue() + c2.getValue();
            System.out.println(p.toString());
            System.out.println("Total " + runningTotal);
            System.out.println();
        }
        System.out.println(housePlayer.toString());
        System.out.println("Total " + housePlayer.getHoldingCards().get(0).getValue() + housePlayer.getHoldingCards().get(1).getValue());
        System.out.println("DealerCardUp is " + round.getDealerCardUp().getValue());
        System.out.println();
        */
        for(Player p: round.getPlayersForThisRound()) {
            // Draw more
            while(runningTotal(p) < 17) {
                p.addCard(round.getCurrentTable().getCardState().retrieve());
            }
        }

        // House draws
        while(runningTotal(housePlayer) < 17) {
            housePlayer.addCard(round.getCurrentTable().getCardState().retrieve());
        }
        for(Player p: round.getPlayersForThisRound()) {
            if (runningTotal(p) > 21) {
                //System.out.println("Player " + p.getPlayerId() + " BUST With " + runningTotal(p));
                housePlayer.setCurrentHoldings(housePlayer.getCurrentHoldings() + p.getRoundBet());
            }
            else if(runningTotal(p) == 21 && runningTotal(housePlayer) == 21) {
                //System.out.println("Player " + p.getPlayerId() + " BLACKJACK PUSH");
                p.setCurrentHoldings(p.getCurrentHoldings() + p.getRoundBet());
            }
            else if(runningTotal(p) == 21) {
                //System.out.println("Player " + p.getPlayerId() + " BLACKJACK");
                p.setCurrentHoldings(p.getCurrentHoldings() + (long) (p.getRoundBet() * BLACKJACK_PAYBACK));
                housePlayer.setCurrentHoldings(housePlayer.getCurrentHoldings() - (long)(p.getRoundBet() * BLACKJACK_HOUSE_LOSS));
            }
            else if(runningTotal(p) < 21 && runningTotal(housePlayer) > 21) {
                //System.out.println("Player " + p.getPlayerId() + " WIN with HOUSE BUST");
                p.setCurrentHoldings(p.getCurrentHoldings() + (long) (p.getRoundBet() * REGULAR_PAYBACK));
                housePlayer.setCurrentHoldings(housePlayer.getCurrentHoldings() - p.getRoundBet());
            }
            else if(runningTotal(p) == runningTotal(housePlayer)){
                //System.out.println("Player " + p.getPlayerId() + " PUSH");
                p.setCurrentHoldings(p.getCurrentHoldings() + p.getRoundBet());
            }
            else if(runningTotal(p) < runningTotal(housePlayer)) {
                //System.out.println("Player " + p.getPlayerId() + " LOSE with " + runningTotal(p) + " to House's " + runningTotal(housePlayer));
                housePlayer.setCurrentHoldings(housePlayer.getCurrentHoldings() + p.getRoundBet());
            }
            else if(runningTotal(p) > runningTotal(housePlayer)) {
                //System.out.println("Player " + p.getPlayerId() + " WIN with " + runningTotal(p) + " to House's " + runningTotal(housePlayer));
                p.setCurrentHoldings(p.getCurrentHoldings() + (long) (p.getRoundBet() * REGULAR_PAYBACK));
                housePlayer.setCurrentHoldings(housePlayer.getCurrentHoldings() - p.getRoundBet());
            }
        }
        return round;
    }

    private int runningTotal(Player p) {
        if(p == null || p.getHoldingCards() == null)
            return 0;
        int total = 0;
        for(Card c: p.getHoldingCards()) {
            total += c.getValue();
        }
        return total;
    }

    @Caffeine(generateMethodComment = PRESET)
    /*
    Caffeine generated comment:
    System time: 2:49 AM
    Location: Unavailable
    Conditional Developer Warning:
            This method was written late at night.
            But fear not, it has passed all unit tests.
            But then, unit tests were also written late at night.
    Dynamic Developer Warning:
            None
     */
    public Round executeRound(Round round) {
        CardState cardState = round.getCurrentTable().getCardState();
        boolean cardStateEvaluator = examineCardState(cardState);
        if(!cardStateEvaluator) {
            //System.out.println("Shuffle time");
            round.getCurrentTable().setCardState(
                    new CardState(
                            round.getCurrentTable().getBlackJackEntity()));
        }
        for(Player p:round.getPlayersForThisRound()) {
            Card c = cardState.retrieve();
            p.addCard(c);
            //System.out.println("Player is: " + p.getPlayerId() + " and card is: " + c.toString());
        }
        Card hc = cardState.retrieve();
        housePlayer.addCard(hc);
        if(round.getRountNumber() == 1) {
            round.setDealerCardUp(hc);
        }
        round.setRountNumber(round.getRountNumber() + 1);
        return round;
    }

    private boolean examineCardState(CardState cardState) {
        boolean cardStateValidity = true;
        if(cardState.getCardsAvailable().size() < 6)
            cardStateValidity = false;
        return cardStateValidity;
    }
}
