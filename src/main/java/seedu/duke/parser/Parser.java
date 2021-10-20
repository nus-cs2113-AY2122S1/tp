package seedu.duke.parser;

import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.AddExpenditureCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteSingleExpenditureCommand;
import seedu.duke.commands.DeleteMultipleExpenditureCommand;
import seedu.duke.commands.DeleteAllExpenditureCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListRecordsCommand;
import seedu.duke.commands.YearCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.duke.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_AMOUNT;
import static seedu.duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_DELETE_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_INVALID_INDEX_OF_EXPENDITURE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_MONTH_OF_BUDGET;
import static seedu.duke.common.Messages.MESSAGE_INVALID_DATE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LIST_COMMAND;

//import java.time.LocalDate;
//import java.util.Locale;

public class Parser {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

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
            command = prepareListMonthCommand(commandParams);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case YearCommand.COMMAND_WORD:
            command = prepareYearCommand(commandParams);;
            break;
        case HelpCommand.COMMAND_WORD:
        default:
            command = new HelpCommand();
            break;
        }
        return command;
    }

    private Command prepareListMonthCommand(String commandParams) {
        try {
            String listOption = commandParams.substring(2);
            switch (listOption) {
            case ("all"):
                return new ListRecordsCommand();
            default:
                int listMonth = Integer.parseInt(listOption);
                return new ListRecordsCommand(listMonth);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_LIST_COMMAND, ListRecordsCommand.MESSAGE_USAGE));
        }
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
        String[] split = commandParams.substring(2).trim().split("a/|m/", 3);
        assert split[0].equals("");
        double amount = Double.parseDouble(split[1].trim());
        int month = Integer.parseInt(split[2].trim());

        return new AddBudgetCommand(amount, month);
    }

    /**
     * Splits the commandParams into description, amount, date.
     *
     * @param commandParams raw String input
     * @return an AddExpenditureCommand with proper parameters
     */
    private Command prepareAddExpenditureCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("e/|a/|d/", 4);
            assert split[0].equals("");
            String description = split[1].trim();
            double amount = Double.parseDouble(split[2].trim());
            LocalDate date;
            if (split[3].equals("")) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(split[3].trim());
            }
            return new AddExpenditureCommand(description, amount, date);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_AMOUNT, AddExpenditureCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException dtpe) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DATE, AddExpenditureCommand.MESSAGE_USAGE));
        }

    }

    private Command prepareYearCommand(String commandParams) {
        try {
            String directoryOfRecordList = "./data/" + commandParams + ".txt";
            return new YearCommand(directoryOfRecordList);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
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
                return prepareDeleteBudgetCommand(commandParams);
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
     * Splits the commandParams to get which month of budget to be deleted.
     *
     * @param commandParams raw String input
     * @return an DeleteBudgetCommand with proper parameters
     */
    private Command prepareDeleteBudgetCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("b/|m/", 3);
            assert split[0].equals("");
            assert split[1].equals("");
            String indexOfMonthString = split[2].trim();
            int month = Integer.parseInt(indexOfMonthString);
            return new DeleteBudgetCommand(month);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(
                    String.format(MESSAGE_INVALID_MONTH_OF_BUDGET, DeleteBudgetCommand.MESSAGE_USAGE)
            );
        }
    }

    /**
     * Splits the commandParams to get index/s of expenditure to be deleted.
     *
     * @param commandParams raw String input
     * @return an DeleteExpenditureCommand with proper parameters
     */
    private Command prepareDeleteExpenditureCommand(String commandParams) {
        try {
            String[] split = commandParams.trim().split("e/|m/", 3);
            assert split[0].equals("");
            String indexOfMonthString = split[2].trim();
            int month = Integer.parseInt(indexOfMonthString);
            String indexOfExpenditureToBeDeleted = split[1].trim();
            if (indexOfExpenditureToBeDeleted.length() == 1) {
                int index;
                index = Integer.parseInt(indexOfExpenditureToBeDeleted);
                return new DeleteSingleExpenditureCommand(index, month);
            } else if (indexOfExpenditureToBeDeleted.length() > 1) {
                int startIndex;
                int endIndex;
                String[] index_split = indexOfExpenditureToBeDeleted.trim().split("-|",2);
                String index1 = index_split[0].trim();
                String index2 = index_split[1].trim();
                startIndex = Integer.parseInt(index1);
                endIndex = Integer.parseInt(index2);
                return new DeleteMultipleExpenditureCommand(startIndex, endIndex, month);
            }

            return new DeleteAllExpenditureCommand(month);

        } catch (NumberFormatException nfe) {
            return new InvalidCommand(
                    String.format(MESSAGE_INVALID_INDEX_OF_EXPENDITURE, DeleteSingleExpenditureCommand.MESSAGE_USAGE)
            );
        }
    }

}
