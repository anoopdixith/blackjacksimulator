package impl;

/**
 * Created by adixith.
 */
public class Constants {
    //Game related
    public static final double BLACKJACK_PAYBACK = 3;
    public static final double REGULAR_PAYBACK = 2;
    public static final double BLACKJACK_HOUSE_LOSS = 2;
    public static final double PROFIT_MARGIN_1 = 1.2;
    public static final double PROFIT_MARGIN_2 = 1.5;
    public static final double PROFIT_MARGIN_3 = 1.75;
    public static final double PROFIT_MARGIN_4 = 2;
    public static final double PROFIT_MARGIN_5 = 2.25;
    public static final double PROFIT_MARGIN_6 = 2.5;
    public static final double PROFIT_MARGIN_7 = 2.75;
    public static final double PROFIT_MARGIN_8 = 3;

    // Player types

    // Play strategy
    public static final String AGGRESSIVE = "aggressive";
    public static final String DEFENSIVE = "defensive";
    public static final String DEALER_LIKE = "dealerLike";
    public static final String LEARNER = "learner";

    // Betting strategy
    public static final String CONSTANT_BET = "constant_bet";
    public static final String BASED_ON_HOLDINGS_BET = "based_on_holdings_bet";
    public static final String RANDOM_BET = "random_bet";
    public static final String MARTINGALE_BET = "martingale_bet";

    // Financial type
    public static final String POOR = "poor";
    public static final String AVERAGE = "average";
    public static final String MODERATE = "moderate";
    public static final String WELLOFF = "welloff";
    public static final String RICH = "rich";
    public static final String SUPER_RICH = "superrich";
    public static final String MULTIMILLIONAIRE = "multimillionaire";
    public static final String BILLIONAIRE = "billionaire";
}
