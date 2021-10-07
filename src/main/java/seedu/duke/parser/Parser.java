package seedu.duke.parser;

import seedu.duke.commands.*;

public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1].trim();
        Command command;
        switch (commandType) {
        case AddBudgetCommand.COMMAND_WORD:
            command = new AddBudgetCommand();
            break;
        case AddExpenditureCommand.COMMAND_WORD:
            command = new AddExpenditureCommand();
            break;
        case DeleteBudgetCommand.COMMAND_WORD:
            command = new DeleteBudgetCommand();
            break;
        case ListRecordsCommand.COMMAND_WORD:
            command = new ListRecordsCommand();
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        return split.length == 2 ? new String[] { split[0].toLowerCase(), split[1] } : new String[] { split[0].toLowerCase() , "" };
    }
}
