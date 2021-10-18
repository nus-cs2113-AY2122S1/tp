package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;

/**
 * Parses and processes input for the create command.
 */
public class CreateWorkoutParser extends Parser {

    public CreateWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareCreateWorkout(String commandArgs) {
        String workoutName = commandArgs.trim();

        if (workoutName.length() == 0) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
        }

        return new CreateWorkoutCommand(workoutName);
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareCreateWorkout(commandArgs);
    }
}
