package seedu.duke.parser;

import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.AddExpenditureCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListRecordsCommand;

public class Parser {
    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        if (split.length == 2) {
            return new String[]{split[0].toLowerCase(), split[1]};
        }
        return new String[]{split[0].toLowerCase(), ""};
    }

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
            command = prepareAddCommand(commandParams);
            break;
        case AddExpenditureCommand.COMMAND_WORD:
            command = new AddExpenditureCommand();
            break;
        case DeleteBudgetCommand.COMMAND_WORD:
            command = new DeleteBudgetCommand(commandParams);
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

    private Command prepareAddCommand(String commandParams) {
        String addType = commandParams.substring(0,2);
        switch(addType){
        case("-b"):
            return prepareAddBudgetCommand(commandParams);
        }
        return new InvalidCommand();

    }

    private Command prepareAddBudgetCommand(String commandParams) {
        String[] split = commandParams.substring(2).trim().split("-a/|-m/", 3);
        double amount = Double.parseDouble(split[1].trim());
        int date = Integer.parseInt(split[2].trim());

        return new AddBudgetCommand(amount, date);
    }
}
