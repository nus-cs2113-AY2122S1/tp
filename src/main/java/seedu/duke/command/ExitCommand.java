package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Used to exit the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "bye: Closes the program"
            + "\tExample: bye";

    /**
     * Determines if command is ExitCommand.
     * @param command command that the user wants to execute
     * @return true if command is ExitCommand, false otherwise
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
    }
}
