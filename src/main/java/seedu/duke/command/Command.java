package seedu.duke.command;

import seedu.duke.exception.GetJackDException;

/**
 * To relay the parsed user input to the RoutineList and ExerciseList class for execution
 */
public abstract class Command {
    protected static boolean isOver = false;

    /**
     * Provides condition of the loop in Duke class which is later updated in the ExitCommand subclass
     *
     * @return the boolean value to check if the program is over
     */
    public static boolean isExit() {
        return isOver;
    }

    /**
     * Abstract method to execute user commands
     *
     * @throws GetJackDException if the subclasses have any exceptions
     */
    public abstract void executeUserCommand() throws GetJackDException;
}
