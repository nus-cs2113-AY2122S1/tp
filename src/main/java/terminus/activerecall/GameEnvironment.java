package terminus.activerecall;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import terminus.common.Messages;
import terminus.content.Question;
import terminus.ui.Ui;

public class GameEnvironment {

    private final Ui ui;
    private final QuestionGenerator questionGenerator;

    public GameEnvironment(Ui ui, List<Question> questionList, int questionCount) {
        this.ui = ui;
        this.questionGenerator = new QuestionGenerator(questionList, questionCount);
    }
    
    public void run() {
        showPreGameInformation();
        while (questionGenerator.hasNext()) {
            Question question = questionGenerator.next();
            promptQuestion(question);
            int difficulty = getUserFeedback();
            if (difficulty == -1) {
                break;
            }
            postQuestionFeedback();
        }
        ui.printSection("Ending the training session.", "Returning you back to main program.");
    }

    private void showPreGameInformation() {
        int questions = questionGenerator.getQuestionPool();
        ui.printSection(
            "---[Active Recall]---",
            "",
            "We will be starting your active recall training session.",
            String.format("This session will consist of %d questions.", questions),
            ""
        );
        ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
    }

    private void promptQuestion(Question question) {
        Instant start = Instant.now(); 
        ui.printSection(
            "",
            "---",
            "",
            "Question:",
            question.getQuestion(),
            ""
        );
        ui.getUserInput("Press [Enter] to reveal the answer.");
        
        long duration = Duration.between(start, Instant.now()).getSeconds();
        ui.printSection(
            String.format("You took %d seconds to reveal the answer.", duration),
            "",
            "Answer:", 
            question.getAnswer()
        );
    }

    private int getUserFeedback() {
        int difficulty = 0;
        do {
            ui.printSection(
                "",
                "How did you find the question? (Compare against past attempts if any)",
                "[1] Easy; [2] Normal; [3] Hard; [E] Exit"
            );
            String input = ui.getUserInput("[1/2/3/E] >> ");
            switch (input) {
            case "1":
            case "2":
            case "3":
                difficulty = Integer.parseInt(input);
                break;
            case "e":
            case "E":
                difficulty = -1;
            }
            
            if (difficulty == 0) {
                ui.printSection("Invalid input provided!");
            }
        } while (difficulty == 0);
        assert difficulty <= 3 && difficulty >= -1;
        return difficulty;
    }
    
    private void postQuestionFeedback() {
        // TODO: Tweak Question Difficulty Here
        ui.printSection("");
        if (questionGenerator.hasNext()) {
            ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
        }
    }

}
