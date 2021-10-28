package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.CreateShelfCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkUpCommand;
import seedu.duke.command.RemoveShelfCommand;
import seedu.duke.command.ReportCommand;
import seedu.duke.command.SellCommand;

import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.parser.exception.NoPropertyFoundException;
import seedu.duke.parser.exception.IllegalFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Parser class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java

/**
 * Parser Class. Manages parsing of user input for different commands.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern REPORT_DATA_ARGS_FORMAT =
        Pattern.compile("t/(?<type>(stats|items))"
            + " ym/(?<startYearMonth>[0-9]{4}-[0-9]{2})"
            + "( ym/(?<endYearMonth>[0-9]{4}-[0-9]{2}))?$"); // optional argument category

    public static final Pattern CREATE_SHELF_DATA_ARGS_FORMAT =
        Pattern.compile("shlv/(?<shelfName>[^/]+)");

    public static final Pattern REMOVE_SHELF_DATA_ARGS_FORMAT =
        Pattern.compile("shlv/(?<shelfName>[^/]+)");

    public static final Pattern SELL_ITEM_DATA_ARGS_FORMAT =
        Pattern.compile("shlv/(?<shelfName>[^/]+) i/(?<indexInShelf>[0-9]+)");

    public static final Pattern MARKUP_ITEM_DATA_ARGS_FORMAT =
        Pattern.compile("shlv/(?<shelfName>[^/]+) i/(?<indexInShelf>[0-9]+)"
            + "( %/(?<percent>([0-9]+([.][0-9]{1,2})?)))?$");

    public static final String REPORT_DATA_ARGS_FORMAT_STRING = "report t/TYPE ym/YEAR-MONTH [ym/YEAR-MONTH]";
    public static final String CREATE_DATA_ARGS_FORMAT_STRING = "create shlv/SHELF_NAME";
    public static final String REMOVE_DATA_ARGS_FORMAT_STRING = "remove shlv/SHELF_NAME";
    public static final String SELL_DATA_ARGS_FORMAT_STRING = "Sell shlv/SHELF_NAME i/INDEX";
    public static final String MARKUP_DATA_ARGS_FORMAT_STRING = "markup shlv/SHELF_NAME i/INDEX [%/PERCENT]";

    public static final String BYE_STRING = "bye";
    public static final String HELP_STRING = "help";
    public static final String REPORT_STRING = "report";
    public static final String CREATE_STRING = "create";
    public static final String REMOVE_STRING = "remove";
    public static final String SELL_STRING = "sell";
    public static final String MARKUP_STRING = "markup";

    public static final String INVALID_BYE_COMMAND = "Error: Type 'bye' without additional parameters to exit";
    public static final String INVALID_HELP_COMMAND = "Error: Type 'help' without additional parameters";

    public static final String CORRECT_COMMAND_MESSAGE_STRING_FORMAT =
        "Input invalid command format.\nCorrect format: \n%s\n";
    public static final String PARSE_REPORT_SUCCESS_MESSAGE_FORMAT = "type: %s\nstart date: %s\nend date: %s\n";
    public static final String PARSE_CREATE_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    public static final String PARSE_REMOVE_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    public static final String PARSE_SELL_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\nindex: %s\n";
    public static final String PARSE_MARKUP_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\nindex: %s\npercent: %s\n";

    public static final String PARSE_SUCCESS_MESSAGE_STRING = "Parsed successful.\n";
    public static final String INVALID_COMMAND_MESSAGE_STRING = "Invalid command, please try again.";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Parses the User input line. Checks the user input line against the basic command format
     * and extracts the command word which is the first word in the user input line. After
     * extraction of the command word, pass the remaining user input line arguments to the
     * respective cases depending on the command word.
     *
     * @param userInputLine The user input Line
     * @return Command object depending on the command type
     * @throws IllegalFormatException   If user input line does not match the respective command format
     * @throws ItemNotExistException    If item name not found in the container
     * @throws NoPropertyFoundException If edit command operation cannot find the associated property specified
     *                                  by the user
     */
    public Command parseCommand(String userInputLine) throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {
        logger.log(Level.INFO, "Parsing Start...");
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Basic Command Format");
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        Command command;

        switch (commandWord) {
        case AddCommand.ADD_STRING:
            command = prepareAdd(arguments);
            break;

        case DeleteCommand.DELETE_STRING:
            command = prepareDelete(arguments);
            break;

        case ListCommand.LIST_STRING:
            command = prepareList(arguments);
            break;

        case GetCommand.GET_STRING:
            command = prepareGet(arguments);
            break;

        case EditCommand.EDIT_STRING:
            command = prepareEdit(arguments);
            break;

        case HELP_STRING:
            command = prepareHelp(arguments);
            break;

        case BYE_STRING:
            return prepareBye(arguments);

        case REPORT_STRING:
            return prepareReport(arguments);

        case SELL_STRING:
            return prepareSell(arguments);

        case CREATE_STRING:
            return prepareCreateShelf(arguments);

        case REMOVE_STRING:
            return prepareRemoveShelf(arguments);

        case MARKUP_STRING:
            return prepareMarkUp(arguments);

        default:
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        return command;
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return AddCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareAdd(String arguments) throws IllegalFormatException {
        final Matcher matcher = AddCommand.ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Add Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, AddCommand.ADD_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String shelfName = matcher.group("shelfName");
        String purchaseCost = matcher.group("purchaseCost");
        String sellingPrice = matcher.group("sellingPrice");
        String quantity = matcher.group("quantity");
        String remarks = matcher.group("remarks");

        Command addCommand;
        if (remarks == null) {
            addCommand = new AddCommand(itemName, purchaseCost, sellingPrice,
                quantity, shelfName, "");
        } else {
            addCommand = new AddCommand(itemName, purchaseCost, sellingPrice,
                quantity, shelfName, remarks);
        }
        assert addCommand.getClass() == AddCommand.class : "Add should return AddCommand\n";
        logger.log(Level.INFO, "AddCommand parse success.");
        return addCommand;
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return DeleteCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareDelete(String arguments) throws IllegalFormatException {
        final Matcher matcher = DeleteCommand.DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Delete Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DeleteCommand.DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");

        Command deleteCommand = new DeleteCommand(shelfName, indexInShelf);
        assert deleteCommand.getClass() == DeleteCommand.class : "Delete should return DeleteCommand\n";
        logger.log(Level.INFO, "DeleteCommand parse success.");
        return deleteCommand;
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return ListCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareList(String arguments) throws IllegalFormatException {
        final Matcher matcher = ListCommand.LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match List Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ListCommand.LIST_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command listCommand;
        if (shelfName == null) {
            listCommand = new ListCommand();
        } else {
            listCommand = new ListCommand(shelfName);
        }

        assert listCommand.getClass() == ListCommand.class : "List should return ListCommand\n";
        logger.log(Level.INFO, "ListCommand parse success.");
        return listCommand;
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return GetCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareGet(String arguments) throws IllegalFormatException {
        final Matcher matcher = GetCommand.GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Get Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GetCommand.GET_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");

        Command getCommand = new GetCommand(shelfName, indexInShelf);
        assert getCommand.getClass() == GetCommand.class : "Get should return GetCommand\n";
        logger.log(Level.INFO, "GetCommand parse success.");
        return getCommand;
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return EditCommand object
     * @throws IllegalFormatException   If the input format is wrong
     * @throws ItemNotExistException    If the item cannot be found from the container
     * @throws NoPropertyFoundException If the associated item property cannot be found
     */
    private Command prepareEdit(String arguments) throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {
        final Matcher matcher = EditCommand.EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Edit Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EditCommand.EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");
        String selectedProperty = matcher.group("property");
        String newValue = matcher.group("value");

        Command editCommand = new EditCommand(shelfName, indexInShelf,
            selectedProperty, newValue);
        assert editCommand.getClass() == EditCommand.class : "Edit should return EditCommand\n";
        logger.log(Level.INFO, "EditCommand parse success.");
        return editCommand;
    }

    /**
     * Parses help command.
     *
     * @param arguments The additional arguments after command word, should be none
     * @return HelpCommand object
     * @throws IllegalFormatException if exists extra argument after bye
     */
    private Command prepareHelp(String arguments) throws IllegalFormatException {
        if (!arguments.isEmpty()) {
            throw new IllegalFormatException(INVALID_HELP_COMMAND);
        }
        return new HelpCommand();
    }

    /**
     * Parses bye command.
     *
     * @param arguments The additional arguments after command word, should be none
     * @return ByeCommand object
     * @throws IllegalFormatException if exists extra argument after bye
     */
    private Command prepareBye(String arguments) throws IllegalFormatException {
        if (!arguments.isEmpty()) {
            throw new IllegalFormatException(INVALID_BYE_COMMAND);
        }
        return new ExitCommand();
    }

    /**
     * Parses report command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return TotalCostAndIncomeCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareReport(String arguments) throws IllegalFormatException {
        final Matcher matcher = REPORT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match List Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, REPORT_DATA_ARGS_FORMAT_STRING));
        }

        String type = matcher.group("type");
        String startYearMonth = matcher.group("startYearMonth");
        String endYearMonth = matcher.group("endYearMonth");

        Command reportCommand = new ReportCommand(startYearMonth, endYearMonth, type);
        assert reportCommand.getClass() == ReportCommand.class :
            "report should return reportCommand\n";
        logger.log(Level.INFO, "TotalCostAndIncomeCommand parse success.");
        return reportCommand;
    }

    /**
     * Parses sell command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return SellCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareSell(String arguments) throws IllegalFormatException {
        final Matcher matcher = SELL_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Sell Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, SELL_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");

        Command sellCommand = new SellCommand(shelfName, indexInShelf);
        assert sellCommand.getClass() == SellCommand.class :
            "report should return createShelfCommand\n";
        logger.log(Level.INFO, "SellCommand parse success.");
        return sellCommand;
    }

    /**
     * Parses create shelf command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return CreateShelfCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareCreateShelf(String arguments) throws IllegalFormatException {
        final Matcher matcher = CREATE_SHELF_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Create Shelf Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, CREATE_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command createShelfCommand = new CreateShelfCommand(shelfName);
        assert createShelfCommand.getClass() == CreateShelfCommand.class :
            "report should return createShelfCommand\n";
        logger.log(Level.INFO, "CreateShelfCommand parse success.");
        return createShelfCommand;
    }

    /**
     * Parses remove shelf command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return RemoveShelfCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareRemoveShelf(String arguments) throws IllegalFormatException {
        final Matcher matcher = REMOVE_SHELF_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Remove Shelf Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, REMOVE_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command removeShelfCommand = new RemoveShelfCommand(shelfName);
        assert removeShelfCommand.getClass() == RemoveShelfCommand.class :
            "remove should return removeShelfCommand\n";
        logger.log(Level.INFO, "RemoveShelfCommand parse success.");
        return removeShelfCommand;
    }

    /**
     * Parses mark up command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return MarkUpCommand object
     * @throws IllegalFormatException If arguments do not follow input format specified
     */
    private Command prepareMarkUp(String arguments) throws IllegalFormatException {
        final Matcher matcher = MARKUP_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Sell Command Format");
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, MARKUP_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");
        String userRequestPercent = matcher.group("percent");

        Command markUpCommand = new MarkUpCommand(shelfName, indexInShelf, userRequestPercent);
        assert markUpCommand.getClass() == MarkUpCommand.class :
            "report should return MarkUpCommand\n";
        logger.log(Level.INFO, "MarkUpCommand parse success.");
        return markUpCommand;
    }
}
