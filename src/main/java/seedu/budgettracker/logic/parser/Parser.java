package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddCommand;
import seedu.budgettracker.logic.commands.Command;
import seedu.budgettracker.logic.commands.DeleteCommand;
import seedu.budgettracker.logic.commands.EditCommand;
import seedu.budgettracker.logic.commands.ExitCommand;
import seedu.budgettracker.logic.commands.FindCommand;
import seedu.budgettracker.logic.commands.HelpCommand;
import seedu.budgettracker.logic.commands.InvalidCommand;
import seedu.budgettracker.logic.commands.ListRecordsCommand;
import seedu.budgettracker.logic.commands.StatCommand;
import seedu.budgettracker.logic.commands.YearCommand;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.common.exception.EmptyDescriptionException;

import java.util.HashMap;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_DELETE_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_STAT_COMMAND;

public class Parser {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Offset after reading type identifier tag. */
    public static final int TYPE_IDENTIFIER_END_INDEX = 2;

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

    public static HashMap<String, String> splitArguments(String commandParams, String[] prefixes) {
        HashMap<String, String> argumentMap = new HashMap<>();
        for (String prefix : prefixes) {
            String parameterValue;
            int startIndex = commandParams.indexOf(prefix) + 2;
            if (startIndex == 1) {
                argumentMap.put(prefix, "");
                continue;
            }
            String substring = commandParams.substring(startIndex);
            if (substring.contains("/")) {
                int endIndex = substring.indexOf("/") - 2;
                parameterValue = substring.substring(0, endIndex);
            } else {
                parameterValue = substring;
            }
            argumentMap.put(prefix, parameterValue);
        }
        return argumentMap;
    }

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
        try {
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
                command = prepareYearCommand(commandParams);
                break;
            case HelpCommand.COMMAND_WORD:
                command = new HelpCommand();
                break;
            case FindCommand.COMMAND_WORD:
                command = prepareFindCommand(commandParams);
                break;
            case EditCommand.COMMAND_WORD:
                command = prepareEditCommand(commandParams);
                break;
            case StatCommand.COMMAND_WORD:
                command = prepareStatCommand(commandParams);
                break;
            default:
                command = new InvalidCommand("Sorry. I don't understand your command!");
                break;
            }
        } catch (NumberFormatException e) {
            command = new InvalidCommand("Month/ Index inputs don't exist! They are compulsory!");
        } catch (EmptyDescriptionException e) {
            command = new InvalidCommand("Expenditure description is empty!");
        }
        return command;
    }

    private Command prepareStatCommand(String commandParams) {
        String statOption = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
        switch (statOption) {
        case ("-c"):
            return StatCategoryParser.parse(commandParams);
        case ("-l"):
            return StatYearParser.parse(commandParams);
        default:
            return new InvalidCommand(MESSAGE_INVALID_STAT_COMMAND);
        }
    }

    private Command prepareEditCommand(String commandParams) throws EmptyDescriptionException {
        String editOption = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
        String paramsToEdit = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
        try {
            switch (editOption) {
            case ("-b"):
                return EditBudgetParser.parse(paramsToEdit);
            case ("-e"):
                return EditExpenditureParser.parse(paramsToEdit);
            case ("-l"):
                return EditLoanParser.parse(paramsToEdit);
            default:
                return new InvalidCommand("Missing inputs! Please indicate '-e', '-b' or '-l");
            }
        } catch (NumberFormatException | EmptyDescriptionException e) {
            return new InvalidCommand("Missing inputs!");
        }
    }

    private Command prepareListMonthCommand(String commandParams) {
        try {
            return ListRecordParser.parse(commandParams);
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
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
            String addType = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
            String addParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (addType) {
            case ("-b"):
                return AddBudgetParser.parse(addParams);
            case ("-e"):
                return AddExpenditureParser.parse(addParams);
            case ("-l"):
                return AddLoanParser.parse(addParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }

    }

    private Command prepareFindCommand(String commandParams) {
        try {
            return new FindCommand(commandParams);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format("Add message later please", AddCommand.MESSAGE_USAGE));
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
     * Splits params into the different delete commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareDeleteCommand(String commandParams) {
        try {
            String deleteType = commandParams.substring(0, TYPE_IDENTIFIER_END_INDEX);
            String deleteParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (deleteType) {
            case ("-b"):
                return DeleteBudgetParser.parse(deleteParams);
            case ("-e"):
                return DeleteExpenditureParser.parse(deleteParams);
            case ("-l"):
                return DeleteLoanParser.parse(deleteParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DELETE_COMMAND, DeleteCommand.MESSAGE_USAGE));
        }
    }
}