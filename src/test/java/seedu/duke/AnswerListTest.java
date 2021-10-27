package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.testing.AnswerList;
import seedu.duke.testing.TestHistory;
import seedu.duke.testing.TestManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerListTest {
    @Test
    public void getScore_noAnswers_expectZero() {
        Deck deck = new Deck();
        AnswerList answerList = new AnswerList(deck);
        assertEquals(0, answerList.getUserScore());
    }

    @Test
    public void getScore_oneCorrectAnswer_expectOne() {
        Deck deck = new Deck();
        DeckManager deckManager = new DeckManager();
        TestHistory testHistory = new TestHistory(deckManager);
        TestManager testManager = new TestManager(testHistory, deckManager);
        deck.addFlashCard("card", "card");
        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("card", 1);
        testManager.markTest(answerList);
        assertEquals(1, answerList.getUserScore());
    }
}
