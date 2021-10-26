package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.RecommendWorkoutCommand;
import seedu.duke.exception.GetJackDException;

//@@author KishorKumar11

/**
 * Parses and processes input for the recommend command.
 */
public class RecommendWorkoutParser extends Parser {

    public RecommendWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareRecommendWorkout(String commandArgs) {

        try {
            String workoutLevel = commandArgs.trim();
            return new RecommendWorkoutCommand(workoutLevel);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + RecommendWorkoutCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareRecommendWorkout(commandArgs);
    }
}
