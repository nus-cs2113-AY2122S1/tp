package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.ClearCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.exception.GetJackDException;

//@@author KishorKumar11

/**
 * Parses and processes input for the clear command.
 */
public class ClearParser extends Parser {
    public static final int INDEX_AFTER_EXERCISE = 8;

    public ClearParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareClear(String commandArgs) {
        try {
            if (commandArgs.equals("workout")) {
                return new ClearCommand("workout");
            } // Checks if the first word of commandArgs is "exercise" or not
            else if (commandArgs.split(" ")[0].equals("exercise")) {
                String argsForExercise = commandArgs.trim().substring(INDEX_AFTER_EXERCISE);
                int workoutIndex = parseWorkoutIndex(argsForExercise);
                return new ClearCommand(workoutIndex, "exercise");
            } else {
                throw new GetJackDException("Invalid Format - No mention of workout/exercise");
            }
        } catch (GetJackDException | NullPointerException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + ClearCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareClear(commandArgs);
    }
}
