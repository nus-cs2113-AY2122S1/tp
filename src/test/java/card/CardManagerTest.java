package card;

import data.card.Card;
import data.card.CardManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class CardManagerTest {
    CardManager cards;

    @BeforeEach
    void setUp() {
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card("Loops can execute a block of code as long as a specified condition is reached.", 1, 0));
        cardList.add(new Card("Arrays are used to store multiple values in a single variable", 2, 0));
        cardList.add(new Card("Use the if statement to specify code to be executed if a condition is true.", 3, 0));
        cards = new CardManager(cardList);
    }

    @Test
    public void testDeleteCard() {
        String content = "Loops can execute a block of code as long as a specified condition is reached.";
        Assertions.assertTrue(cards.deleteCard(1).toString().equals(content));
    }

    @Test
    public void testExchange() {
        Assertions.assertTrue(cards.exchange(1));
    }
}
