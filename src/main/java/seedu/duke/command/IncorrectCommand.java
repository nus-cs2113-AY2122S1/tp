package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * For indicating that the command entered by the user is incorrect or invalid.
 * Feedback to user is set at point of instantiation.
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser;
    private static Logger LOGGER = Logger.getLogger(IncorrectCommand.class.getName());

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        assert feedbackToUser != null;
        setupLogger(LOGGER);
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        LOGGER.info("Invalid command entered");
        throw new GetJackDException(feedbackToUser);
    }

}
