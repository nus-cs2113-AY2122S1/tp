package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.parser.exceptions.ParserException;
import seedu.budgettracker.logic.commands.AddCommand;
import seedu.budgettracker.logic.commands.CsvCommand;
import seedu.budgettracker.logic.commands.Command;
import seedu.budgettracker.logic.commands.DbCommand;
import seedu.budgettracker.logic.commands.DeleteCommand;
import seedu.budgettracker.logic.commands.EditCommand;
import seedu.budgettracker.logic.commands.ExitCommand;
import seedu.budgettracker.logic.commands.FindCommand;
import seedu.budgettracker.logic.commands.HelpCommand;
import seedu.budgettracker.logic.commands.InvalidCommand;
import seedu.budgettracker.logic.commands.ListRecordsCommand;
import seedu.budgettracker.logic.commands.StatCommand;
import seedu.budgettracker.logic.commands.YearCommand;

import java.util.HashMap;

import static seedu.budgettracker.common.Messages.MESSAGE_INCORRECT_OR_MISSING_INPUTS;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_DELETE_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_STAT_COMMAND;
import static seedu.budgettracker.common.Messages.MESSAGE_MISSING_TYPE_INPUTS;
import static seedu.budgettracker.common.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.budgettracker.common.Messages.MESSAGE_WARNING_INCORRECT_YEAR_FORMAT;

/**
 * Main Parser class which parses user input.
 */
public class Parser {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int INDEX_OFFSET_DISPLAYED = 1;
    public static final int INDEX_OFFSET_TYPE = 2;

    /** Offset after reading type identifier tag. */
    public static final int TYPE_IDENTIFIER_END_INDEX = 2;
    public static final int TYPE_IDENTIFIER_START_INDEX = 0;

    public static final int INDEX_SPLIT_ARRAY_TYPE = 0;
    public static final int INDEX_SPLIT_ARRAY_PARAMS = 1;

    public static final String PREFIX_TYPE_CATEGORY = "-c";
    public static final String PREFIX_TYPE_YEAR = "-y";
    public static final String PREFIX_TYPE_BUDGET = "-b";
    public static final String PREFIX_TYPE_EXPENDITURE = "-e";
    public static final String PREFIX_TYPE_LOAN = "-l";

    /**
     * Splits the user input into the command word and arguments.
     *
     * @param userInput the raw input of the user as a String
     * @return a String array with the command word in index 0 and the arguments in index 1
     */
    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        if (split.length == 2) {
            return new String[]{split[0].toLowerCase(), split[1]};
        }
        return new String[]{split[0].toLowerCase(), ""};
    }

    //@@author jyxhazcake
    /**
     * Splits the command arguments by their command prefixes.
     *
     * @param prefixParams the raw command arguments string
     * @param prefixes an array of prefix strings that the command should split by
     * @return a HashMap of String keys and values, where keys are command prefixes,
     *     and values are their respective values
     */
    public static HashMap<String, String> splitArgs(String prefixParams, String[] prefixes) throws ParserException {
        int counter = 0;
        HashMap<String, String> argumentMap = new HashMap<>();
        for (String prefix : prefixes) {
            if (!prefixParams.contains(prefix)) {
                argumentMap.put(prefix, "");
                continue;
            }
            String argValue = findArgValue(prefixParams, prefix);
            argumentMap.put(prefix, argValue);
            counter++;
        }
        checkValidArguments(prefixParams, counter);
        return argumentMap;
    }

    //@@author jyxhazcake
    /**
     * Checks whether the arguments provided are valid.
     *
     * @param prefixParams the arguments from the user command with a "/" prefix
     * @param counter a value that denotes the correct length of an array split by its arguments
     * @throws ParserException if arguments are invalid
     */
    private static void checkValidArguments(String prefixParams, int counter) throws ParserException {
        String[] split = prefixParams.split("[a-zA-Z]/");
        if (split.length - INDEX_OFFSET_DISPLAYED > counter) {
            throw new ParserException(MESSAGE_TOO_MANY_ARGUMENTS);
        }
        if (!split[INDEX_SPLIT_ARRAY_TYPE].trim().equals("")) {
            throw new ParserException(MESSAGE_INCORRECT_OR_MISSING_INPUTS);
        }
    }

    //@@author jyxhazcake
    /**
     * Finds the value of the argument from the corresponding prefix.
     *
     * @param prefixParams the arguments from the user command with a "/" prefix
     * @param prefix a string which denotes the type of arguments (e.g. "n/", "d/", etc.)
     * @return the argument value that corresponds to the prefix
     */
    private static String findArgValue(String prefixParams, String prefix) {
        int startIndex = prefixParams.indexOf(prefix) + INDEX_OFFSET_TYPE;
        String substring = prefixParams.substring(startIndex);
        String argValue;
        if (substring.contains("/")) {
            int endIndex = substring.indexOf("/") - INDEX_OFFSET_TYPE;
            argValue = substring.substring(TYPE_IDENTIFIER_START_INDEX, endIndex);
        } else {
            argValue = substring;
        }
        return argValue;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) throws ArrayIndexOutOfBoundsException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        String commandType = commandTypeAndParams[INDEX_SPLIT_ARRAY_TYPE];
        String commandParams = commandTypeAndParams[INDEX_SPLIT_ARRAY_PARAMS].trim();
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
            case CsvCommand.COMMAND_WORD:
                command = new CsvCommand();
                break;
            case EditCommand.COMMAND_WORD:
                command = prepareEditCommand(commandParams);
                break;
            case StatCommand.COMMAND_WORD:
                command = prepareStatCommand(commandParams);
                break;
            case DbCommand.COMMAND_WORD:
                command = new DbCommand();
                break;
            default:
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
                break;
            }
        } catch (ParserException e) {
            command = new InvalidCommand(e.getMessage());
        }
        return command;
    }

    //@@author YEOWEIHNGWHYELAB
    /**
     * Splits the stat command parameters into its respective type and parses accordingly.
     *
     * @param commandParams the arguments from the user command after the command keyword
     * @return the corresponding Command for the input
     * @throws ParserException if arguments are invalid
     */
    private Command prepareStatCommand(String commandParams) throws ParserException {
        String statOption = commandParams.substring(TYPE_IDENTIFIER_START_INDEX, TYPE_IDENTIFIER_END_INDEX);
        String statParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
        switch (statOption) {
        case (PREFIX_TYPE_CATEGORY):
            return StatCategoryParser.parse(statParams);
        case (PREFIX_TYPE_YEAR):
            return StatYearParser.parse(statParams);
        default:
            return new InvalidCommand(MESSAGE_INVALID_STAT_COMMAND);
        }
    }

    //@@author jyxhazcake
    /**
     * Splits the edit command parameters into its respective type and parses accordingly.
     *
     * @param commandParams the arguments from the user command after the command keyword
     * @return the corresponding Command for the input
     * @throws ParserException if arguments are invalid
     */
    private Command prepareEditCommand(String commandParams) throws ParserException {
        try {
            String editOption = commandParams.substring(TYPE_IDENTIFIER_START_INDEX, TYPE_IDENTIFIER_END_INDEX);
            String paramsToEdit = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (editOption) {
            case (PREFIX_TYPE_BUDGET):
                return EditBudgetParser.parse(paramsToEdit);
            case (PREFIX_TYPE_EXPENDITURE):
                return EditExpenditureParser.parse(paramsToEdit);
            case (PREFIX_TYPE_LOAN):
                return EditLoanParser.parse(paramsToEdit);
            default:
                return new InvalidCommand(MESSAGE_MISSING_TYPE_INPUTS);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ParserException(String.format(MESSAGE_MISSING_TYPE_INPUTS, EditCommand.MESSAGE_USAGE));
        }
    }

    //@@author YEOWEIHNGWHYELAB
    /**
     * Prepares the list command for parsing.
     *
     * @param commandParams the arguments from the user command after the command keyword
     * @return the corresponding Command for the input
     * @throws ParserException if arguments are invalid
     */
    private Command prepareListMonthCommand(String commandParams) throws ParserException {
        try {
            return ListRecordParser.parse(commandParams);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new ParserException(String.format(MESSAGE_INVALID_LIST_COMMAND, ListRecordsCommand.MESSAGE_USAGE));
        }
    }

    //@@author jyxhazcake
    /**
     * Splits the add command parameters into its respective type and parses accordingly.
     *
     * @param commandParams the arguments from the user command after the command keyword
     * @return the corresponding Command for the input
     * @throws ParserException if arguments are invalid
     */
    private Command prepareAddCommand(String commandParams) throws ParserException {
        try {
            String addType = commandParams.substring(TYPE_IDENTIFIER_START_INDEX, TYPE_IDENTIFIER_END_INDEX);
            String addParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (addType) {
            case (PREFIX_TYPE_BUDGET):
                return AddBudgetParser.parse(addParams);
            case (PREFIX_TYPE_EXPENDITURE):
                return AddExpenditureParser.parse(addParams);
            case (PREFIX_TYPE_LOAN):
                return AddLoanParser.parse(addParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new ParserException(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }

    }

    //@@author YEOWEIHNGWHYELAB
    /**
     * Prepares the find command for parsing.
     *
     * @param commandParams the arguments from the user command after the command keyword
     * @return the corresponding Command for the input
     * @throws ParserException if arguments are invalid
     */
    private Command prepareFindCommand(String commandParams) throws ParserException {
        try {
            return new FindCommand(commandParams);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ParserException(String.format("Add message later please", AddCommand.MESSAGE_USAGE));
        }
    }

    //@@author YEOWEIHNGWHYELAB
    private Command prepareYearCommand(String commandParams) {
        try {
            boolean isYear = commandParams.matches("^[0-9]{4}$");

            if (!(isYear)) {
                return new InvalidCommand(String.format(MESSAGE_WARNING_INCORRECT_YEAR_FORMAT,
                        AddCommand.MESSAGE_USAGE));
            }
            int year = Integer.parseInt(commandParams);
            String directoryOfRecordList = "./data/" + commandParams + ".txt";
            return new YearCommand(directoryOfRecordList, year);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_ADD_COMMAND, AddCommand.MESSAGE_USAGE));
        }
    }

    //@@author jyxhazcake
    //@@author EdisonZhong17
    /**
     * Splits params into the different delete commands.
     *
     * @param commandParams raw String commandParams
     * @return AddBudgetCommand or AddExpenditureCommand depending on the addType
     */
    private Command prepareDeleteCommand(String commandParams) throws ParserException {
        try {
            String deleteType = commandParams.substring(TYPE_IDENTIFIER_START_INDEX, TYPE_IDENTIFIER_END_INDEX);
            String deleteParams = commandParams.substring(TYPE_IDENTIFIER_END_INDEX);
            switch (deleteType) {
            case (PREFIX_TYPE_BUDGET):
                return DeleteBudgetParser.parse(deleteParams);
            case (PREFIX_TYPE_EXPENDITURE):
                return DeleteExpenditureParser.parse(deleteParams);
            case (PREFIX_TYPE_LOAN):
                return DeleteLoanParser.parse(deleteParams);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(MESSAGE_INVALID_DELETE_COMMAND, DeleteCommand.MESSAGE_USAGE));
        }
    }
}