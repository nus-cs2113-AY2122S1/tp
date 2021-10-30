package terminus.activerecall;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Question;
import terminus.ui.Ui;

public class GameEnvironment {

    private static final int INVALID_DIFFICULTY = 0;
    public static final int EASY_DIFFICULTY = 1;
    public static final int NORMAL_DIFFICULTY = 2;
    public static final int HARD_DIFFICULTY = 3;
    public static final int EXIT_CODE = -1;
    public static final String EXIT_STRING = "e";
    private final Ui ui;
    private final QuestionGenerator questionGenerator;

    GameEnvironment(Ui ui, QuestionGenerator generator) {
        this.ui = ui;
        this.questionGenerator = generator;
    }

    /**
     * Starts the active recall session.
     */
    public void run() {
        showPreGameInformation();
        while (questionGenerator.hasNext()) {
            Question question = promptQuestion();
            int difficulty = getUserFeedback();
            if (difficulty == EXIT_CODE) {
                break;
            }
            updateQuestionDifficulty(question, difficulty);
        }
        ui.printSection(Messages.ACTIVE_RECALL_SESSION_END_MESSAGE);
    }

    private void showPreGameInformation() {
        int questions = questionGenerator.getQuestionPoolSize();
        ui.printSection(
            "---[Active Recall]---",
            "",
            "We will be starting your active recall training session.",
            String.format("This session will consist of %d questions.", questions),
            ""
        );
        ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
    }

    private Question promptQuestion() {
        Question question = questionGenerator.next();
        Instant start = Instant.now(); 
        ui.printSection(
            "",
            "---",
            "",
            "Question:",
            question.getQuestion(),
            ""
        );
        ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
        
        long duration = Duration.between(start, Instant.now()).getSeconds();
        ui.printSection(
            String.format("You took %d seconds to reveal the answer.", duration),
            "",
            "Answer:", 
            question.getAnswer()
        );
        return question;
    }

    private int getUserFeedback() {
        int difficulty = INVALID_DIFFICULTY;
        do {
            ui.printSection(Messages.ACTIVE_RECALL_ASK_QUESTION_DIFFICULTY_MESSAGE);
            String input = ui.getUserInput(Messages.MESSAGE_QUESTION_PROMPT).trim().toLowerCase();
            Pattern inputPattern = Pattern.compile(CommonFormat.QUESTION_FORMAT_CHECK);
            Matcher matcher = inputPattern.matcher(input);
            if (!matcher.matches()) {
                ui.printSection(Messages.ERROR_MESSAGE_INVALID_INPUT);
                continue;
            } else if (input.equalsIgnoreCase(EXIT_STRING)) {
                difficulty = EXIT_CODE;
                break;
            }
            difficulty = Integer.parseInt(input);
            
        } while (difficulty == INVALID_DIFFICULTY);
        assert difficulty <= HARD_DIFFICULTY && difficulty >= -EASY_DIFFICULTY;
        return difficulty;
    }
    
    private void updateQuestionDifficulty(Question question, int difficulty) {
        assert difficulty >= EASY_DIFFICULTY && difficulty <= HARD_DIFFICULTY;
        double weight = question.getWeight();
        if (difficulty == EASY_DIFFICULTY) {
            double newWeight = DifficultyModifier.tweakEasyQuestionDifficulty(weight);
            question.setWeight(newWeight);
        } else if (difficulty == HARD_DIFFICULTY) {
            double newWeight = DifficultyModifier.tweakHardQuestionDifficulty(weight);
            question.setWeight(newWeight);
        }
        ui.printSection("");
        if (questionGenerator.hasNext()) {
            ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
        }
    }

    /**
     * Create a new GameEnvironment instance using the singleton instance of Ui.
     * 
     * @param questions The list of questions to ask from.
     * @param questionCount The maximum number of questions.
     * @return The new GameEnvironment to start the Active Recall.
     */
    public static GameEnvironment createNewEnvironment(List<Question> questions, int questionCount) {
        return new GameEnvironment(Ui.getInstance(), new QuestionGenerator(questions, questionCount));
    }

}
