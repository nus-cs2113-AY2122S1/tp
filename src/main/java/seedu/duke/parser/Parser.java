package seedu.duke.parser;

import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.AddExpenditureCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.DeleteExpenditureCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListRecordsCommand;

import java.time.LocalDate;

public class Parser {

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        if (split.length == 2) {
            return new String[]{split[0].toLowerCase(), split[1]};
        }
        return new String[]{split[0].toLowerCase(), ""};
    }

    //    public static String[] splitExpenditureParams(String expenditureParams) {
    //        return expenditureParams.split(" ", 3);
    //    }

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
        case AddCommand.COMMAND_WORD:
            command = prepareAddCommand(commandParams);
            break;
        case DeleteExpenditureCommand.COMMAND_WORD:
            command = new DeleteExpenditureCommand(commandParams);
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

    /**
     * Splits params into the different add commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareAddCommand(String commandParams) {
        String addType = commandParams.substring(0, 2);
        switch (addType) {
        case ("b/"):
            return prepareAddBudgetCommand(commandParams);
        case ("e/"):
            return prepareAddExpenditureCommand(commandParams);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Splits the commandParams into amount and date.
     *
     * @param commandParams String with raw input
     * @return an AddBudgetCommand with proper parameters
     */
    private Command prepareAddBudgetCommand(String commandParams) {
        String[] split = commandParams.substring(2).trim().split("a/", 2);
        double amount = Double.parseDouble(split[1].trim());
        //int date = Integer.parseInt(split[2].trim());

        return new AddBudgetCommand(amount);
    }

    /**
     * Splits the commandParams into description, amount, date.
     *
     * @param commandParams raw String input
     * @return an AddExpenditureCommand with proper parameters
     */
    private Command prepareAddExpenditureCommand(String commandParams) {
        String[] split = commandParams.trim().split("e/|a/", 3);
        String description = split[1].trim();
        double amount = Double.parseDouble(split[2].trim());
        //LocalDate date = LocalDate.parse(split[3].trim());

        return new AddExpenditureCommand(description, amount);
    }

}
