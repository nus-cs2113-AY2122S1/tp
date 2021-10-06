package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

/**
 * To relay the parsed user input to the WorkoutList and ExerciseList class for execution.
 */
public abstract class Command {
    public static final String MESSAGE_WORKOUT_NOT_FOUND = "Error. Workout not found\n";
    public static final String MESSAGE_EXERCISE_NOT_FOUND = "Error. Exercise not found\n";

    protected Command() {
    }

    /**
     * Abstract method to execute user commands.
     *
     * @throws GetJackDException if the subclasses have any exceptions
     */
    public abstract void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException;
}
