package card;

import data.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CardTest {
    @Test
    public void testOutput() {
        Assertions.assertTrue(new Card("loop", 1, 0).toString().equals("loop"));
    }

    @Test
    public void testIsUsed() {
        Assertions.assertFalse(new Card("loop", 1, 0).checkIfIsUsed());
    }
}
