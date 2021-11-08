package data.card;


import java.io.Serializable;


public class Card implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private String content;
    private boolean isUsed;
    private int cardID;
    private boolean isCollected;
    private int level;  // 0 for easy and 1 for hard

    public Card(String content, int cardID, int level) {
        this.content = content;
        this.cardID = cardID;
        this.isUsed = false;
        this.isCollected = false;
        this.level = level;
    }

    public Card(Card card) {   // for cloning card
        this(card.getContent(), card.getCardID(), card.level);
    }

    public Card(Card card, boolean isUsed) {   // for listing card
        this.content = card.getContent();
        this.cardID = card.getCardID();
        this.isUsed = isUsed;
        this.isCollected = true;
        this.level = card.getCardLevel();
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardIndex(int index) {
        cardID = index;
    }

    public int getCardLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public boolean checkIfIsUsed() {
        return isUsed;
    }

    public boolean checkIfCollected() {
        return isCollected;
    }

    public void setAsUsed() {
        isUsed = true;
    }

    public Card setAsCollected() {
        Card card = new Card(this);
        card.isCollected = true;
        return card;
    }

    @Override
    public String toString() {
        return getContent();
    }
}