package data.card;

import utils.message.Strings;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class CardManager implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private List<Card> cards;
    private static final String MARK_NOT_USED = "[ ]";
    private static final String MARK_USED = "[x]";
    private static final int NUM_OF_CARDS_PER_GAME = 10;     // 10 may need to be changed later on

    public CardManager(List<Card> cards) {
        this.cards = cards;
    }

    public CardManager() {
        this(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
        //System.out.println("Great! You collect card: " + card.toString());
    }

    /**
     * find card that is not collected within a certain range.
     * @return the index of the card in the ArrayList, if the cards in the range are all collected, return -1
     */
    public int getCardPosition(int startID) {
        int index = -1;
        int startIndex = startID - 1;
        for (int i = startIndex; i < startIndex + NUM_OF_CARDS_PER_GAME; i++) {
            if (!cards.get(i).checkIfCollected()) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * find the index of the card in the cardList by cardID.
     * @return -1 if the cardID is not found
     */
    public int findCard(int id) {
        try {
            boolean isValid = id > 0 && id <= cards.size();
            assert isValid : "Ops, it seems that you input an invalid card id, please try again!";
            int len = cards.size();
            int index = -1;
            for (int i = 0; i < len; i++) {
                if (cards.get(i).getCardID() == id) {
                    index = i;
                    break;
                }
            }
            return index;
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }


    /**
     * list all the cards by keyword.
     * @param keyword the keyword user input
     */
    public void findCardByKeyword(String keyword) {
        int found = 0;
        System.out.println("Here are all the cards found:");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getContent().contains(keyword)) {
                System.out.println(i + ". " +  card.getContent());
                found++;
            }
        }
        if (found == 0) {
            System.out.println("Ops! It seems that you do not have a card containing that keyword!");
        }
    }

    /**
     * list the card by id.
     * @param id the id user input
     */
    public void findPrintCard(int id) {
        int index = findCard(id);
        assert index != -1 : "Should not print this line.";
        System.out.println("Your card " + id + " : " + cards.get(id - 1) + " has been found!");
    }

    public void searchByKeyWord(String message) {
        int count = 0;
        for (Card currentCard : cards) {
            if (currentCard.getContent().contains(message)) {
                System.out.println(currentCard);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Ops! It seems that you do not have any card containing the keyword");
        }
    }

    public void listCards() {
        int len = cards.size();
        List<Card> easyCardList = new ArrayList<>();
        List<Card> diffCardList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Card card = cards.get(i);
            Card newCard = new Card(card, card.checkIfIsUsed());
            int id = i + 1;
            newCard.setCardIndex(id);
            int level = newCard.getCardLevel();
            if (level == 0) {
                easyCardList.add(newCard);
            } else {
                diffCardList.add(newCard);
            }
        }
        System.out.println(Strings.DIVIDER);
        System.out.println("Easy-level cards collected: ");
        for (Card card : easyCardList) {
            int id = card.getCardID();
            String label = card.checkIfIsUsed() ? MARK_USED : MARK_NOT_USED;
            System.out.println(id + ". " + card + " " + label);
        }
        System.out.println(Strings.DIVIDER);
        System.out.println("Difficult-level cards collected: ");
        for (Card card : diffCardList) {
            int id = card.getCardID();
            String label = card.checkIfIsUsed() ? MARK_USED : MARK_NOT_USED;
            System.out.println(id + ". " + card + " " + label);
        }
        System.out.println(Strings.DIVIDER);
    }

    public Card deleteCard(int id) {
        boolean isValid = id > 0 && id <= cards.size();
        assert isValid : "Ops, it seems that you input an invalid card id, please try again!";
        System.out.println("Your card " + id + " : " + cards.get(id - 1) + " has been removed!");
        return cards.remove(id - 1);
    }

    public void setAsCollected(int index) {
        Card cardToCollect = cards.get(index);
        cardToCollect.setAsCollected();
    }

    /**
     * enter this function means you are sure to win the card and there are enough cards
     * print the card message when you win the card.
     * @param cardsToTransfer  CardsCollectd
     * @param index  the index of the card in the ArrayList of the CardsToBeCollected
     * @return the cardID of the card collected
     */
    public int transferTo(CardManager cardsToTransfer, int index) {
        Card cardToCollect = cards.get(index).setAsCollected();
        System.out.println("Great, this is the card you win!\n" + Strings.CARD_DIVIDER_MESSAGE);
        System.out.println(cardToCollect);
        System.out.println(Strings.CARD_DIVIDER_MESSAGE);
        cards.set(index, cardToCollect);   //set the card as "collected" in the cards list
        cardsToTransfer.add(cardToCollect);   //add the "collected" card to cardsToTransfer
        return cardToCollect.getCardID();
    }

    public boolean exchange(int cardID) {
        boolean canBeExchanged = true;
        Card cardSelected = cards.get(cardID - 1);
        if (cardSelected.checkIfIsUsed()) {
            canBeExchanged = false;
        } else {
            cardSelected.setAsUsed();
        }
        return canBeExchanged;
    }

    public int getSize() {
        return cards.size();
    }
}