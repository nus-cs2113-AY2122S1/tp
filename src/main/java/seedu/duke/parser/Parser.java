package seedu.duke.parser;

import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.AddExpenditureCommand;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.DeleteExpenditureCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListRecordsCommand;
import seedu.duke.commands.Command;

import static seedu.duke.common.Messages.*;

//import java.time.LocalDate;
//import java.util.Locale;

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
    public Command parseCommand(String userInput) throws ArrayIndexOutOfBoundsException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1].trim();
        assert commandType.equals(commandType.toLowerCase());
        Command command;
        switch (commandType) {
        case AddCommand.COMMAND_WORD:
            command = prepareAddCommand(commandParams);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = prepareDeleteCommand(commandParams);
            break;
        case ListRecordsCommand.COMMAND_WORD:
            command = new ListRecordsCommand();
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case HelpCommand.COMMAND_WORD:
        default:
            command = new HelpCommand();
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
        try {
            String addType = commandParams.substring(0, 2);
            switch (addType) {
            case ("b/"):
                return prepareAddBudgetCommand(commandParams);
            case ("e/"):
                return prepareAddExpenditureCommand(commandParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Splits the commandParams into amount and date.
     *
     * @param commandParams String with raw input
     * @return an AddBudgetCommand with proper parameters
     * @throws ArrayIndexOutOfBoundsException if amount input does not exist.
     */
    private Command prepareAddBudgetCommand(String commandParams) throws ArrayIndexOutOfBoundsException {
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
        try {
            String[] split = commandParams.trim().split("e/|a/", 3);
            String description = split[1].trim();
            double amount = Double.parseDouble(split[2].trim());
            //LocalDate date = LocalDate.parse(split[3].trim());
            return new AddExpenditureCommand(description, amount);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_AMOUNT, AddExpenditureCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Splits params into the different add commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareDeleteCommand(String commandParams) {
        try {
            String deleteType = commandParams.substring(0, 2);
            switch (deleteType) {
            case ("b/"):
                return new DeleteBudgetCommand(commandParams);
            case ("e/"):
                return prepareDeleteExpenditureCommand(commandParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DELETE_COMMAND, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits the commandParams to get index of expenditure to be deleted.
     *
     * @param commandParams raw String input
     * @return an DeleteExpenditureCommand with proper parameters
     */
    private Command prepareDeleteExpenditureCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("/|", 2);
            String indexString = split[1].trim();
            int index = Integer.parseInt(indexString);
            return new DeleteExpenditureCommand(index);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_INDEX_OF_EXPENDITURE, DeleteExpenditureCommand.MESSAGE_USAGE));
        }
    }

}
