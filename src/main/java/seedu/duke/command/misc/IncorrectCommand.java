package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author jonathanmui4

/**
 * For indicating that the command entered by the user is incorrect or invalid.
 * Feedback to user is set at point of instantiation.
 */
public class IncorrectCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(IncorrectCommand.class.getName());
    public final String feedbackToUser;

    /**
     * Instantiates object and sets parameters for displaying the error message for incorrect commands.
     *
     * @param feedbackToUser is the message shown to user
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        assert feedbackToUser != null;
        setupLogger(LOGGER);
    }

    /**
     * Executes command to show that the user has written an invalid command.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @throws GetJackDException if invalid command is used
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        LOGGER.info("Invalid command entered");
        throw new GetJackDException(feedbackToUser);
    }

}
