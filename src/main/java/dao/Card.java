package dao;

/**
 * Created by adixith.
 */
public class Card {
    public int getValue() {
        return value;
    }

    public int getSymbol() {
        return symbol;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    private int value;
    private int symbol;
    FaceValue faceValue;

    public Card(int value, int symbol, FaceValue faceValue) {
        this.symbol = symbol;
        this.value = value;
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        String readableString = "\nValue is: " + value + " Symbol is: " + symbol + " faceValue is: " + faceValue;
        return readableString;
    }
}
