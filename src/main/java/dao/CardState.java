package dao;

import java.util.*;

/**
 * Created by adixith.
 */
public class CardState {
    public List<Card> getCardsAvailable() {
        return cardsAvailable;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    private  List<Card> cardsAvailable;
    private int numberOfDecks;

    public CardState(BlackJackEntity blackJackEntity) {
        this.numberOfDecks = blackJackEntity.getNumberOfDecks();
        this.cardsAvailable = getFreshShuffle(numberOfDecks);
    }

    public List<Card> currentCardState(BlackJackEntity blackJackEntity) {
        return cardsAvailable;
    }

    private List<Card> getFreshShuffle(int numberOfDecks) {
        List<Card> allCards = new ArrayList<Card>();
        while(numberOfDecks-- > 0) {
            for(int value=1; value <= 10; value++) {
                for(int symbol=1; symbol <= 4; symbol++) {
                    Card c = new Card(value, symbol, null);
                    allCards.add(c);
                }
            }
        }
        for(int symbol = 1; symbol <= 4; symbol++) {
            Card c = new Card(1, symbol, FaceValue.A);
            allCards.add(c);
            c = new Card(10, symbol, FaceValue.J);
            allCards.add(c);
            c = new Card(10, symbol, FaceValue.Q);
            allCards.add(c);
            c = new Card(10, symbol, FaceValue.K);
            allCards.add(c);
        }
        return shuffle(allCards, 1);
    }

    private List<Card> shuffle(List<Card> allCards, int shufflingAlgorithmId) {
        Collections.shuffle(allCards);
        return allCards;
    }

    public Card retrieve() {
        if(cardsAvailable.isEmpty()) {
            cardsAvailable = getFreshShuffle(numberOfDecks);
        }
        Card card = cardsAvailable.get(0);
        cardsAvailable.remove(0);
        return card;
    }

    @Override
    public String toString() {
        String numOfCards = "Num of cards: " + cardsAvailable.size();
        String cardsPresent = "\n Cards: " + cardsAvailable.toString();
        String readable = numOfCards.concat(cardsPresent);
        return readable;
    }
}
