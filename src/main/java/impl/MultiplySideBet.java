package impl;

import dao.*;

/**
 * Created by adixith.
 */
public class MultiplySideBet {
    public static void main(String a[]) {
        int numberOfDecks = 5;
        int bet = 10;
        double twoCardNoAceReturn = 2;
        double twoCardReturn = 2;
        double bothAceReturn = 3;
        double threeCardReturn = 4;
        double fourOrMoreCardReturn = 50;
        int numberOfRounds = 100;
        BlackJackEntity blackJackEntity = new BlackJackEntity(numberOfDecks);

        double totalReturn = 0;
        for(int round =0; round < numberOfRounds; round++) {
            double returnValue = new MultiplySideBet().simulateMyltiplyBet(
                    blackJackEntity,
                    bet,
                    twoCardReturn,
                    twoCardNoAceReturn,
                    bothAceReturn,
                    threeCardReturn,
                    fourOrMoreCardReturn);
            System.out.println("Bet is " + bet + " Return value is " + returnValue);
            totalReturn += returnValue;
        }

        System.out.println("=======");
        int totalBet = numberOfRounds * bet;
        double net = (totalReturn - (numberOfRounds * bet));
        double percentage = (totalReturn / totalBet) * 100;
        System.out.println("Total bet is " + totalBet + ". Total Return is " + totalReturn + ". Net is " + net + ". Perc is " + percentage + "%");
    }

    private double simulateMyltiplyBet(BlackJackEntity blackJackEntity, int  bet,
                                     double twoCardReturn, double twoCardNoAceReturn,
                                     double bothAceReturn, double threeCardReturn,
                                     double fourOrMoreCardReturn) {
        CardState cardState = new CardState(blackJackEntity);
        double returnValue;
        Card firstCard = cardState.retrieve();
        Card secondCard = cardState.retrieve();
        Card thirdCard;
        Card fourthCard;
        System.out.println("Firstcard is " + firstCard.getValue() + " " + firstCard.getFaceValue());
        System.out.println("Secondcard is " + secondCard.getValue() + " " + secondCard.getFaceValue());
        int product = firstCard.getValue() * secondCard.getValue();
        int sum = firstCard.getValue() + secondCard.getValue();
        // Lose bet of greater than BlackJack value
        if(product > 21) {
            return 0;
        }
        if(firstCard.getFaceValue() != FaceValue.A && secondCard.getFaceValue() != FaceValue.A) {
            returnValue = bet * twoCardNoAceReturn;
        }
        else if(firstCard.getFaceValue() == FaceValue.A && secondCard.getFaceValue() == FaceValue.A) {
            returnValue = bet * bothAceReturn;
        }
        else {
            returnValue = bet * twoCardReturn;
        }
        // Drawable
        if(sum < 17 || (sum == 17 && (firstCard.getFaceValue() == FaceValue.A || secondCard.getFaceValue() == FaceValue.A))) {
             thirdCard = cardState.retrieve();
             System.out.println("Thirdcard is " + thirdCard.getValue() + " " + thirdCard.getFaceValue());
             sum += thirdCard.getValue();
             product *= thirdCard.getValue();
             if(product <= 21) {
                 returnValue = bet * threeCardReturn;
             }
            // Drawable
            if(sum < 17 || (sum == 17 && (firstCard.getFaceValue() == FaceValue.A
                    || secondCard.getFaceValue() == FaceValue.A
                    || thirdCard.getFaceValue() == FaceValue.A))) {
                fourthCard = cardState.retrieve();
                System.out.println("Fourthcard is " + fourthCard.getValue() + " " + fourthCard.getFaceValue());
                sum += fourthCard.getValue();
                product *= fourthCard.getValue();
                if(product <= 21) {
                    returnValue = bet * fourOrMoreCardReturn;
                    System.out.println("===============WOW==============");
                }
            }

        }
        return returnValue;
    }
}
