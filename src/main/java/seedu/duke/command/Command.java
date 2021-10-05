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

    protected static boolean isOver = false;

    protected Command() {
    }

    /**
     * Provides condition of the loop in Duke class which is later updated in the ExitCommand subclass.
     *
     * @return the boolean value to check if the program is over
     */
    public static boolean isExit() {
        return isOver;
    }

    /**
     * Abstract method to execute user commands.
     *
     * @throws GetJackDException if the subclasses have any exceptions
     */
    public abstract void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException;
}
