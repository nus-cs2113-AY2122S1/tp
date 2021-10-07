package main.java.card;

public class Card {
    private String codeTip;
    private int cardType;

    public Card(String codeTip, int cardType) {
        this.codeTip = codeTip;
        this.cardType = cardType;
    }

    public String getCodeTip() {
        return codeTip;
    }

    public int getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return codeTip;
    }
}
