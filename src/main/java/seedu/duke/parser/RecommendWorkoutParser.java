package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.RecommendWorkoutCommand;

/**
 * Parses and processes input for the recommend command.
 */
public class RecommendWorkoutParser extends Parser {

    public RecommendWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareRecommendWorkout(String commandArgs) {
        String workoutLevel = commandArgs.trim();

        if (workoutLevel.length() == 0) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + RecommendWorkoutCommand.MESSAGE_USAGE);
        }

        return new RecommendWorkoutCommand(workoutLevel);
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareRecommendWorkout(commandArgs);
    }
}
