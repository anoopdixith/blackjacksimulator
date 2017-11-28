package impl;

import dao.*;
import static impl.Constants.*;
import java.util.*;

/**
 * Created by adixith.
 */
public class Utils {
    public static Player getRandomPlayer(String playerType,
                                         double playerQuitStrategy,
                                         String playerPlayStrategy,
                                         String playerBetStrategy) {
        long seed = 0;
        switch (playerType.toLowerCase()) {
            case POOR: seed = 100; break;
            case AVERAGE: seed = 500; break;
            case MODERATE: seed = 1000; break;
            case WELLOFF: seed = 5000; break;
            case RICH: seed = 10000; break;
            case SUPER_RICH: seed = 100000; break;
            case MULTIMILLIONAIRE: seed = 1000000; break;
            case BILLIONAIRE: seed = 50000000; break;
            default: seed = 10000;
        }
        //Player p = new Player((long)(Math.random() * seed));
        Player p = new Player((long)(seed));
        p.setPlayerTypeFinancially(playerType);
        p.setPlayerTypePlayStrategy(playerPlayStrategy);
        p.setPlayerTypeQuitStrategy(playerQuitStrategy);
        p.setPlayerTypeBettingStrategy(playerBetStrategy);
        return p;
    }

    public static List<Player> getPlayers(int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        while(numberOfPlayers-- > 0) {
            players.add(getRandomPlayer(randomizePlayerFinancialType(),
                    randomizePlayerQuitStrategy(),
                    randomizePlayerPlayStrategy(),
                    randomizePlayerBettingStrategy()));
        }
        return players;
    }

    private static double randomizePlayerQuitStrategy() {
        int typeInt = (int) (Math.random() * 5);
        double quitQuotient = 0;
        switch (typeInt) {
            case 1: quitQuotient = 1.2; break;
            case 2: quitQuotient = 1.4; break;
            case 3: quitQuotient = 1.6; break;
            case 4: quitQuotient = 1.8; break;
            case 5: quitQuotient = 2; break;
            default: quitQuotient = 1.5;
        }
        return quitQuotient;
    }

    private static String randomizePlayerPlayStrategy() {
        int typeInt = (int) (Math.random() * 4);
        String playerType = "";
        switch (typeInt) {
            case 1: playerType = AGGRESSIVE; break;
            case 2: playerType = DEFENSIVE; break;
            case 3: playerType = DEALER_LIKE; break;
            case 4: playerType = LEARNER; break;
            default: playerType = DEALER_LIKE;
        }
        return playerType;
    }

    private static String randomizePlayerBettingStrategy() {
        int typeInt = (int) (Math.random() * 4);
        String playerType = "";
        switch (typeInt) {
            case 1: playerType = CONSTANT_BET; break;
            case 2: playerType = BASED_ON_HOLDINGS_BET; break;
            case 3: playerType = RANDOM_BET; break;
            case 4: playerType = MARTINGALE_BET; break;
            default: playerType = CONSTANT_BET;
        }
        return playerType;
    }

    private static String randomizePlayerFinancialType() {
        int typeInt = (int) (Math.random() * 8);
        String playerType = "";
        switch (typeInt) {
            case 1: playerType = POOR; break;
            case 2: playerType = AVERAGE; break;
            case 3: playerType = MODERATE; break;
            case 4: playerType = WELLOFF; break;
            case 5: playerType = RICH; break;
            case 6: playerType = SUPER_RICH; break;
            case 7: playerType = MULTIMILLIONAIRE; break;
            case 8: playerType = BILLIONAIRE; break;
            default: playerType = MODERATE;
        }
        return playerType;
    }

    private static class KickThePlayerOutException extends Exception {
        public KickThePlayerOutException(String message) {
            super(message);
        }
    }
}
