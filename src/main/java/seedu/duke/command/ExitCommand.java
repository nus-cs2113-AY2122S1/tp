package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

/**
 * Used to exit the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Function which makes the condition to exit the program true.
     *
     * @return the boolean value to end the program
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
    }
}
