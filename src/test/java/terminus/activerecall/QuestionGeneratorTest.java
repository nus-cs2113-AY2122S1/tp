package terminus.activerecall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Question;

public class QuestionGeneratorTest {

    private Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
        random.setSeed(1L);
    }

    @Test
    void hasNext_noQuestions_returnsFalse() {
        QuestionGenerator questionGenerator = new QuestionGenerator(new ArrayList<>(), 10);
        assertFalse(questionGenerator.hasNext());
    }

    @Test
    void next_noQuestions_throwsException() {
        QuestionGenerator questionGenerator = new QuestionGenerator(new ArrayList<>(), 10);
        assertThrows(NullPointerException.class, questionGenerator::next);
    }

    @Test
    void next_success() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        QuestionGenerator questionGenerator = new QuestionGenerator(questions, 10, random);
        for (int i = 0; i < 10; i++) {
            Question question = questionGenerator.next();
            questions.remove(question);
        }
        assertTrue(questions.isEmpty());

        for (int i = 0; i < 10; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        questionGenerator = new QuestionGenerator(questions, 100, random);
        for (int i = 0; i < 10; i++) {
            questions.remove(questionGenerator.next());
        }
        assertTrue(questions.isEmpty());
        assertFalse(questionGenerator.hasNext());

        for (int i = 0; i < 10; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        questionGenerator = new QuestionGenerator(questions, 5, random);
        for (int i = 0; i < 5; i++) {
            questions.remove(questionGenerator.next());
        }
        assertEquals(5, questions.size());
        assertFalse(questionGenerator.hasNext());
    }
}
