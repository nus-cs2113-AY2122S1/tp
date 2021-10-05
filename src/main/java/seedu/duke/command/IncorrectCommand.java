package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        System.out.println(feedbackToUser);
    }

}
