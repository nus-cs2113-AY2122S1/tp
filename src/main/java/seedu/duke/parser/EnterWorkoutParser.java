package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.exception.GetJackDException;

//@@author IshaaanVyas

/**
 * Parses and processes input for the enter command.
 */
public class EnterWorkoutParser extends Parser {

    /**
     * Instantiates object by setting userInputString.
     *
     * @param userInputString input from user
     */
    public EnterWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    /**
     * Prepares and returns a EnterWorkoutCommand.
     *
     * @param commandArgs arguments that the user inputs together with the command
     *                    (workoutIndex in this case)
     * @return EnterWorkoutCommand corresponding to the commandArgs
     */
    private Command prepareEnterWorkout(String commandArgs) {
        try {
            int workoutIndex = parseWorkoutIndex(commandArgs);
            return new EnterWorkoutCommand(workoutIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + EnterWorkoutCommand.MESSAGE_USAGE);
        }
    }

    /**
     * parses the userInput and returns a EnterWorkoutCommand Object.
     *
     * @return EnterWorkoutCommand object which may be executed to enter a particular Workout
     */
    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareEnterWorkout(commandArgs);
    }
}
