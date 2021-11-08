package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.misc.SearchCommand;

//@@author qqkoh

public class SearchParser extends Parser {

    public SearchParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareSearch(String commandArgs) {
        if (commandArgs.trim().isEmpty()) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + SearchCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + SearchCommand.MESSAGE_USAGE_MAIN);
        }
        return new SearchCommand(commandArgs);


    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareSearch(commandArgs);
    }

}
