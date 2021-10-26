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
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + SearchCommand.MESSAGE_USAGE);
        }
        return new SearchCommand(commandArgs);
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareSearch(commandArgs);
    }

}
