package terminus.activerecall;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Question;
import terminus.ui.Ui;

public class GameEnvironmentTest {

    private static final String LS = System.lineSeparator();
    private Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
        random.setSeed(1L);
    }

    @Test
    void run_exit_success() {
        String input = String.format("%s%s2%s%s%se%s", LS, LS, LS, LS, LS, LS);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Ui ui = new Ui(in);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        QuestionGenerator questionGenerator = new QuestionGenerator(questions, 3, random);
        GameEnvironment gameEnvironment = new GameEnvironment(ui, questionGenerator);
        gameEnvironment.run();
        for (int i = 0; i < 5; i++) {
            assertEquals(0.5, questions.get(i).getWeight());
        }
    }

    @Test
    void run_ignoreGarbageInput_success() {
        String input = String.format("%s%sasdf%s2%s%s%sasdf%se%s", LS, LS, LS, LS, LS, LS, LS, LS);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Ui ui = new Ui(in);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        QuestionGenerator questionGenerator = new QuestionGenerator(questions, 3, random);
        GameEnvironment gameEnvironment = new GameEnvironment(ui, questionGenerator);
        gameEnvironment.run();
        for (int i = 0; i < 5; i++) {
            assertEquals(0.5, questions.get(i).getWeight());
        }
    }


    @Test
    void run_reweigh_success() {
        String input = String.format("%s%s3%s%s%s2%s%s%s1%s%s%s2%s%s%s2%s", LS, LS, LS, LS, LS, LS, LS, LS, LS, LS,
                LS, LS, LS, LS, LS);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Ui ui = new Ui(in);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            questions.add(new Question("test " + i, "answer " + i));
        }
        QuestionGenerator questionGenerator = new QuestionGenerator(questions, 10, random);
        GameEnvironment gameEnvironment = new GameEnvironment(ui, questionGenerator);
        gameEnvironment.run();
        double highNewWeight = DifficultyModifier.tweakHardQuestionDifficulty(0.5);
        double lowNewWeight = DifficultyModifier.tweakEasyQuestionDifficulty(0.5);
        int high = 0;
        int same = 0;
        int low = 0;
        for (int i = 0; i < 5; i++) {
            if (questions.get(i).getWeight() == highNewWeight) {
                high++;
            } else if (questions.get(i).getWeight() == 0.5) {
                same++;
            } else if (questions.get(i).getWeight() == lowNewWeight) {
                low++;
            }
        }
        assertEquals(1, high);
        assertEquals(1, low);
        assertEquals(3, same);
    }
}
