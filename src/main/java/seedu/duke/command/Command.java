package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * To relay the parsed user input to the WorkoutList and ExerciseList class for execution.
 */
public abstract class Command {
    public static final String ERROR_MESSAGE_WORKOUT_NOT_FOUND = "Error. Workout not found\n";
    public static final String ERROR_MESSAGE_EXERCISE_NOT_FOUND = "Error. Exercise not found\n";
    public static String MESSAGE_USAGE;

    protected Command() {
    }

    /**
     * Abstract method to execute user commands.
     *
     * @throws GetJackDException if the subclasses have any exceptions
     */
    public abstract void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException;
}
