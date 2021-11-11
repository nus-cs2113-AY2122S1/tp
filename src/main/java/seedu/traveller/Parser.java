package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.AddItemCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.DeleteDayCommand;
import seedu.traveller.commands.DeleteItemCommand;
import seedu.traveller.commands.ViewCommand;
import seedu.traveller.commands.SearchItemCommand;
import seedu.traveller.commands.EditItemCommand;
import seedu.traveller.commands.ShortestCommand;
import seedu.traveller.commands.AddDayCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.HelpCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.EmptyFieldValueException;
import seedu.traveller.exceptions.IllegalTimeFormatException;
import seedu.traveller.exceptions.IllegalTimeValueException;
import seedu.traveller.exceptions.IllegalTripNameException;
import seedu.traveller.exceptions.InvalidAddDayFormatException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidDeleteDayFormatException;
import seedu.traveller.exceptions.InvalidDeleteItemFormatCommand;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.InvalidNumberOfDaysException;
import seedu.traveller.exceptions.InvalidSearchItemFormatException;
import seedu.traveller.exceptions.InvalidEditItemIndexException;
import seedu.traveller.exceptions.InvalidEditItemFormatException;
import seedu.traveller.exceptions.InvalidShortestTimeException;
import seedu.traveller.exceptions.InvalidShortestCostException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.WorldMap;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Used to understand raw user input.
 */
public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private static final int FROM_LENGTH = 7;
    private static final int TO_LENGTH = 5;
    private static final int DAY_LENGTH = 6;
    private static final int NAME_LENGTH = 7;
    private static final int ITEM_LENGTH = 7;
    private static final int TIME_LENGTH = 7;
    private static final int INDEX_LENGTH = 8;
    private static final int KEY_LENGTH = 6;


    /**
     * Breaks down raw user input into commands understood by <code>Traveller</code>.
     * @param rawInput String of the raw user input.
     * @return Command <code>Command</code> object based on the user input.
     * @throws TravellerException Thrown when the raw user input cannot be understood by the <code>Parser</code>.
     */
    public static Command parse(String rawInput) throws TravellerException {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Parsing raw user input");
        Command command;

        String[] userInput = rawInput.split(" ", 2);
        String userCommand = userInput[0].toLowerCase();
        try {
            switch (userCommand) {
            case "new":
                command = parseNewCommand(userInput[1]);
                break;
            case "view":
                command = parseViewCommand(userInput[1]);
                break;
            case "shortest-time":
                command = parseShortestTimeCommand(userInput[1]);
                break;
            case "shortest-cost":
                command = parseShortestCostCommand(userInput[1]);
                break;
            case "search-item":
                command = parseSearchItemCommand(userInput[1]);
                break;
            case "add-day":
                command = parseAddDayCommand(userInput[1]);
                break;
            case "add-item":
                command = parseAddItemCommand(userInput[1]);
                break;
            case "edit-item":
                command = parseEditItemCommand(userInput[1]);
                break;
            case "exit":
                command = parseExitCommand();
                break;
            case "edit":
                command = parseEditCommand(userInput[1]);
                break;
            case "delete":
                command = parseDeleteCommand(userInput[1]);
                break;
            case "delete-day":
                command = parseDeleteDayCommand(userInput[1]);
                break;
            case "delete-item":
                command = parseDeleteItemCommand(userInput[1]);
                break;
            case "help":
                command = parseHelpCommand();
                break;
            default:
                logger.log(Level.WARNING, "Invalid command input!");
                throw new CommandNotFoundException(rawInput);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException();
        }
        return command;
    }


    //@@author Uxinnn
    /**
     * Parses user input to give an <code>AddItemCommand</code>.
     * @param userInput Raw user input, with the first command option (add-item) removed.
     * @return Command An <code>AddItemCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseAddItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Add-item command input");
        String tripName;
        String itemName;
        String itemTime;
        String rawDayNumber;

        try {
            int dayIdx = getDayFlagIndex(userInput);
            int timeIdx = getTimeFlagIndex(userInput);
            int nameIdx = getNameFlagIndex(userInput);

            tripName = parseFieldValue(userInput, 0, dayIdx);
            rawDayNumber = parseFieldValue(userInput, dayIdx + DAY_LENGTH, timeIdx);
            itemTime = parseFieldValue(userInput, timeIdx + TIME_LENGTH, nameIdx);
            itemName = parseFieldValue(userInput, nameIdx + NAME_LENGTH, userInput.length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        parseValidTime(itemTime);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command = new AddItemCommand(tripName, dayIndex, itemTime, itemName);

        return command;
    }

    //@@author Uxinnn
    /**
     * Parses user input to give a <code>NewCommand</code>.
     * @param userInput Raw user input, with the first command option (new) removed.
     * @return Command A <code>NewCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseNewCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "New command input");
        Command command;
        String tripName;
        String startCountryCode;
        String endCountryCode;
        try {
            int fromIdx = getFromFlagIndex(userInput);
            int toIdx = getToFlagIndex(userInput);

            tripName = parseFieldValue(userInput, 0, fromIdx);
            startCountryCode = parseFieldValue(userInput,
                    fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            endCountryCode = parseFieldValue(userInput,
                    toIdx + TO_LENGTH, userInput.length()).toUpperCase();
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid new command format: " + userInput);
            throw new InvalidNewFormatException();
        }

        parseValidTripName(tripName);

        command = new NewCommand(tripName, startCountryCode, endCountryCode);
        return command;
    }

    //@@author
    /**
     * Parses user input to give a <code>DeleteCommand</code>.
     * @param userInput Raw user input, with the first command option (delete) removed.
     * @return Command A <code>DeleteCommand</code> object.
     */
    private static Command parseDeleteCommand(String userInput) throws TravellerException {
        Command command;
        logger.log(Level.INFO, "Delete command input");
        String tripName = parseFieldValue(userInput, 0, userInput.length());
        command = new DeleteCommand(tripName);
        return command;
    }

    /**
     * Parses user input to give an <code>EditCommand</code>.
     * @param userInput Raw user input, with the first command option (edit) removed.
     * @return Command An <code>EditCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseEditCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit command input");
        Command command;
        try {
            String newTripName = "";
            String startCountryCode = "";
            String endCountryCode = "";
            if (userInput.contains("/name")) {
                newTripName = parseEditNewTripName(userInput);
            }
            if (userInput.contains("/from")) {
                startCountryCode = parseEditStartCountryCode(userInput);
            }
            if (userInput.contains("/to")) {
                endCountryCode = parseEditEndCountryCode(userInput);
            }
            String tripName = parseEditTripName(userInput);
            parseValidTripName(tripName);
            parseValidTripName(newTripName);
            command = new EditCommand(tripName, newTripName, startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditFormatException();
        }
        return command;
    }

    /**
     * Parses userInput to give <code>TripName</code>.
     * @param userInput Raw user input, with the first command option (edit) removed.
     * @return String Name of the Trip.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditTripName(String userInput) throws TravellerException {
        String tripName;
        if (userInput.contains("/name")) {
            int nameIdx = getNameFlagIndex(userInput);
            tripName = parseFieldValue(userInput, 0, nameIdx);
        } else if (userInput.contains("/from")) {
            assert !userInput.contains("/name");
            int fromIdx = getFromFlagIndex(userInput);
            tripName = parseFieldValue(userInput, 0, fromIdx);
        } else {
            assert !userInput.contains("/name") && !userInput.contains("/from");
            int toIdx = getToFlagIndex(userInput);
            tripName = parseFieldValue(userInput, 0, toIdx);
        }
        return tripName;
    }

    /**
     * Parses userInput to give <code>NewTripName</code>.
     * @param userInput Raw user input, with the first command option (edit) removed.
     * @return String New name of the Trip to be changed to in edit command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditNewTripName(String userInput) throws TravellerException {
        int nameIdx = getNameFlagIndex(userInput);
        String newTripName;
        if (userInput.contains("/from")) {
            int fromIdx = getFromFlagIndex(userInput);
            newTripName = parseFieldValue(userInput, nameIdx + NAME_LENGTH, fromIdx);
        } else if (userInput.contains("/to")) {
            assert !userInput.contains("/from");
            int toIdx = getToFlagIndex(userInput);
            newTripName = parseFieldValue(userInput, nameIdx + NAME_LENGTH, toIdx);
        } else {
            assert (!userInput.contains("/from") && !userInput.contains("/to"));
            newTripName = parseFieldValue(userInput, nameIdx + NAME_LENGTH, userInput.length());
        }
        return newTripName;
    }

    /**
     * Parses userInput to give <code>StartCountryCode</code>.
     * @param userInput Raw user input, with the first command option (edit) removed.
     * @return String Start Country Code to be changed in edit command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditStartCountryCode(String userInput) throws TravellerException {
        int fromIdx = getFromFlagIndex(userInput);
        String startCountryCode;
        if (userInput.contains("/to")) {
            int toIdx = getToFlagIndex(userInput);
            startCountryCode = parseFieldValue(userInput,
                    fromIdx + FROM_LENGTH, toIdx).toUpperCase();
        } else {
            assert (!userInput.contains("/to"));
            startCountryCode = parseFieldValue(userInput,
                    fromIdx + FROM_LENGTH, userInput.length()).toUpperCase();
        }
        return startCountryCode;
    }

    /**
     * Parses userInput to give <code>EndCountryCode</code>.
     * @param userInput Raw user input, with the first command option (edit) removed.
     * @return String End Country Code to be changed in edit command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditEndCountryCode(String userInput) throws TravellerException {
        int toIdx = getToFlagIndex(userInput);
        String endCountryCode = parseFieldValue(userInput,
                toIdx + TO_LENGTH, userInput.length()).toUpperCase();
        return endCountryCode;
    }

    /**
     * Parses user input to give a <code>DeleteDayCommand</code>.
     * @param userInput Raw user input, with the first command option (delete-day) removed.
     * @return Command A <code>DeleteDayCommand</code> object.
     */
    private static Command parseDeleteDayCommand(String userInput) throws TravellerException {
        String tripName;
        String rawDayIndex;

        try {
            int dayIdx = getDayFlagIndex(userInput);

            tripName = parseFieldValue(userInput, 0, dayIdx);
            rawDayIndex = parseFieldValue(userInput, dayIdx + DAY_LENGTH, userInput.length());
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid delete-day command format: " + userInput);
            throw new InvalidDeleteDayFormatException();
        }
        int dayIndex = parseValidIndex(rawDayIndex);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command;
        logger.log(Level.INFO, "Delete-day command input");
        command = new DeleteDayCommand(tripName, dayIndex);
        return command;
    }

    /**
     * Parses user input to give a <code>DeleteItemCommand</code>.
     * @param userInput Raw user input, with the first command option (delete-item) removed.
     * @return A <code>DeleteItemCommand</code> object.
     */
    private static Command parseDeleteItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Delete-item command input");
        String tripName;
        String rawDayNumber;
        String rawItemNumber;
        try {
            int dayIdx = getDayFlagIndex(userInput);
            int itemIdx = getItemFlagIndex(userInput);

            tripName = parseFieldValue(userInput, 0, dayIdx);
            rawDayNumber = parseFieldValue(userInput, dayIdx + DAY_LENGTH, itemIdx);
            rawItemNumber = parseFieldValue(userInput, itemIdx + ITEM_LENGTH, userInput.length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidDeleteItemFormatCommand();
        }

        int dayNumber = parseValidIndex(rawDayNumber);
        assert dayNumber >= 0 : "Day number is negative.";

        int itemNumber = parseValidIndex(rawItemNumber);
        assert itemNumber >= 0 : "Item number is negative.";

        Command command;
        command = new DeleteItemCommand(tripName, dayNumber, itemNumber);
        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give a <code>SearchItemCommand</code>.
     * @param userInput Raw user input, with the first command option (search-item) removed.
     * @return Command An <code>SearchItemCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseSearchItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        String tripName;
        String rawDayNumber;
        String keyword;

        try {
            int dayIdx = getDayFlagIndex(userInput);
            int keywordIdx = getKeyFlagIndex(userInput);

            tripName = parseFieldValue(userInput, 0, dayIdx);
            rawDayNumber = parseFieldValue(userInput, dayIdx + DAY_LENGTH, keywordIdx);
            keyword = parseFieldValue(userInput, keywordIdx + KEY_LENGTH, userInput.length());
            assert !keyword.equals(" ") : "keyword should not be blank.";
            assert !keyword.contains(" ") : "keyword should not be contain whitespace.";

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSearchItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command = new SearchItemCommand(tripName, dayIndex, keyword);

        return command;
    }

    //@@author Uxinnn
    /**
     * Parses user input to give a <code>ViewCommand</code>.
     * @return Command A <code>ViewCommand</code> object.
     */
    private static Command parseViewCommand(String userInput) throws  TravellerException {
        Command command;
        logger.log(Level.INFO, "View command input");
        String tripName = parseFieldValue(userInput, 0, userInput.length());
        command = new ViewCommand(tripName);
        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give an <code>EditItemCommand</code>.
     * @param userInput Raw user input, with the first command option (edit-item) removed.
     * @return Command An <code>EditItemCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseEditItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-item command input");
        Command command;

        try {
            String newTripEventTime = "";
            String newTripEventName = "";
            String tripName;
            int itemIndex;
            String rawDayNumber;

            int dayIndex = getDayFlagIndex(userInput);
            tripName = parseFieldValue(userInput, 0, dayIndex);

            int indexIndex = getIndexFlagIndex(userInput);
            rawDayNumber = parseFieldValue(userInput, dayIndex + DAY_LENGTH, indexIndex);
            itemIndex = parseEditNewTripIndex(userInput);

            int dayAsInteger = parseValidIndex(rawDayNumber);
            assert dayAsInteger >= 0 : "Day index is negative.";

            if (userInput.contains("/time")) {
                newTripEventTime = parseEditNewTripEventTime(userInput);
            }
            if (userInput.contains("/name")) {
                newTripEventName = parseEditNewTripEventName(userInput);
            }

            command = new EditItemCommand(tripName, dayAsInteger, itemIndex,
                    newTripEventTime, newTripEventName);

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditItemFormatException();
        }

        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give an <code>parseEditNewTripIndex</code>.
     * @param userInput Raw user input, with the first command option (edit-item) removed.
     * @return Integer itemIndex to be changed in edit-item command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static int parseEditNewTripIndex(String userInput) throws TravellerException {
        int itemIndex;
        String rawIndex = "";

        int indexIndex = getIndexFlagIndex(userInput);

        if (userInput.contains("/time")) {
            int timeIndex = getTimeFlagIndex(userInput);
            rawIndex = parseFieldValue(userInput, indexIndex + INDEX_LENGTH, timeIndex);
        } else if (userInput.contains("/name")) {
            assert !userInput.contains("/time");
            int nameIndex = getNameFlagIndex(userInput);
            rawIndex = parseFieldValue(userInput, indexIndex + INDEX_LENGTH, nameIndex);
        } else {
            assert !userInput.contains("/name") && !userInput.contains("/time");
        }

        try {
            itemIndex = Integer.parseInt(rawIndex);
        } catch (NumberFormatException e) {
            throw new InvalidEditItemIndexException(rawIndex);
        }
        if (itemIndex < 0) {
            throw new InvalidEditItemIndexException(rawIndex);
        }

        return itemIndex;
    }

    //@@author conradwee
    /**
     * Parses user input to give an <code>parseEditNewTripEventTime</code>.
     * @param userInput Raw user input, with the first command option (edit-item) removed.
     * @return String itemTime to be changed in edit-item command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditNewTripEventTime(String userInput) throws TravellerException {
        int fromIndex = getTimeFlagIndex(userInput);

        String itemTime;

        if (userInput.contains("/name")) {
            int nameIndex = getNameFlagIndex(userInput);
            itemTime = parseFieldValue(userInput,
                    fromIndex + TIME_LENGTH, nameIndex);
        } else {
            assert (!userInput.contains("/name"));
            itemTime = parseFieldValue(userInput,
                    fromIndex + TIME_LENGTH, userInput.length());
        }

        parseValidTime(itemTime);

        return itemTime;
    }

    //@@author conradwee
    /**
     * Parses user input to give an <code>parseEditNewTripEventName</code>.
     * @param userInput Raw user input, with the first command option (edit-item) removed.
     * @return String itemName to be changed in edit-item command.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static String parseEditNewTripEventName(String userInput) throws TravellerException {
        int nameIndex = getNameFlagIndex(userInput);
        String itemName;

        itemName = parseFieldValue(userInput,
                nameIndex + NAME_LENGTH, userInput.length());

        return itemName;
    }


    //@@author Uxinnn
    /**
     * Parses user input to give an <code>AddDayCommand</code>.
     * @param userInput Raw user input, with the first command option (add-day) removed.
     * @return Command An <code>AddDayCommand</code> object.
     */
    private static Command parseAddDayCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Add-day command input");
        String tripName;
        String rawDaysIndex;

        try {
            int dayIdx = getDayFlagIndex(userInput);
            tripName = parseFieldValue(userInput, 0, dayIdx);
            rawDaysIndex = parseFieldValue(userInput, dayIdx + DAY_LENGTH, userInput.length());
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid add-day command format: " + userInput);
            throw new InvalidAddDayFormatException();
        }

        int daysIndex = parseValidIndex(rawDaysIndex);
        if (daysIndex == 0) {
            throw new InvalidNumberOfDaysException(daysIndex);
        }
        assert daysIndex > 0 : "Number of days is negative or 0.";

        Command command;
        command = new AddDayCommand(tripName, daysIndex);
        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give a <code>ShortestCommand</code>.
     * @param userInput Raw user input, with the first command option (shortest-dist) removed.
     * @return Command A <code>ShortestCommand</code> object with in-built tag "dist"
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseShortestTimeCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        String startCountryCode;
        String endCountryCode;

        int fromIndex = getFromFlagIndex(userInput);
        int toIndex = getToFlagIndex(userInput);

        try {
            startCountryCode = parseFieldValue(userInput,
                    fromIndex + FROM_LENGTH, toIndex).toUpperCase();
            endCountryCode = parseFieldValue(userInput,
                    toIndex + TO_LENGTH, userInput.length()).toUpperCase();

            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("time", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestTimeException();
        }

        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give a <code>ShortestCommand</code>.
     * @param userInput Raw user input, with the first command option (shortest-cost) removed.
     * @return Command A <code>ShortestCommand</code> object with in-built tag "cost"
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseShortestCostCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        String startCountryCode;
        String endCountryCode;

        int fromIndex = getFromFlagIndex(userInput);
        int toIndex = getToFlagIndex(userInput);

        try {
            startCountryCode = parseFieldValue(userInput,
                    fromIndex + FROM_LENGTH, toIndex).toUpperCase();
            endCountryCode = parseFieldValue(userInput,
                    toIndex + TO_LENGTH, userInput.length()).toUpperCase();

            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("cost", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestCostException();
        }

        return command;
    }

    //@@author conradwee
    /**
     * Parses user input to give an <code>ExitCommand</code>.
     * @return Command An <code>ExitCommand</code> object.
     */
    private static Command parseExitCommand() {
        logger.log(Level.INFO, "Exit command input");
        return new ExitCommand();
    }

    //@@author conradwee
    /**
     * Launches help menu.
     * @return Command An <code>ExitCommand</code> object.
     */
    private static Command parseHelpCommand() {
        logger.log(Level.INFO, "Help command input");
        return new HelpCommand();
    }

    //@@author Uxinnn
    /**
     * Parses a field value to remove leading and trailing whitespaces and check for validity.
     * @param userInput Raw string that the user has entered.
     * @param startFieldIndex Start index of the field value in <code>userInput</code>.
     * @param endFieldIndex End index of the field value in <code>userInput</code>.
     * @return The processes field value.
     * @throws TravellerException Thrown if the field value is an empty string.
     */
    private static String parseFieldValue(String userInput, int startFieldIndex, int endFieldIndex)
            throws TravellerException {
        String fieldValue = userInput.substring(startFieldIndex, endFieldIndex).trim();
        if (Objects.equals(fieldValue, "")) {
            throw new EmptyFieldValueException();
        }
        return fieldValue;
    }

    //@@author Uxinnn
    /**
     * Used to check if a user input value for the day/item field is valid.
     * @param rawIndex Raw day/item index as inputted by user.
     * @return Day/Item index as an integer.
     * @throws TravellerException If <code>rawIndex</code> is not an integer or is negative.
     */
    private static int parseValidIndex(String rawIndex) throws TravellerException {
        int index;

        try {
            index = Integer.parseInt(rawIndex);
        } catch (NumberFormatException e) {
            throw new InvalidNumberOfDaysException(rawIndex);
        }
        if (index < 0) {
            throw new InvalidNumberOfDaysException(index);
        }
        return index;
    }

    //@@author Uxinnn
    /**
     * Used to check if a user input value for the time field is valid.
     * @param rawTime Raw time as inputted by user.
     * @throws TravellerException If <code>rawTime</code> is not a 4 digit integer between 0000 and 2359 inclusive.
     */
    private static void parseValidTime(String rawTime) throws TravellerException {
        int timeInteger;
        try {
            timeInteger = Integer.parseInt(rawTime);
        } catch (NumberFormatException e) {
            throw new IllegalTimeFormatException(rawTime);
        }
        if (rawTime.length() != 4) {
            throw new IllegalTimeValueException(rawTime);
        }
        if (timeInteger < 0 || timeInteger > 2359) {
            throw new IllegalTimeValueException(rawTime);
        }
    }

    //@@author conradwee
    /**
     * Used to check if a user input value for the tripName field is valid.
     * @param tripName TripName that user has entered.
     * @throws TravellerException If <code>TripName</code> is not a valid <code>TripName</code>.
     */
    private static void parseValidTripName(String tripName) throws TravellerException {
        if (tripName.equals("all") || tripName.contains("/")) {
            throw new IllegalTripNameException(tripName);
        }
    }

    //@@author Uxinnn
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /from flag.
     * @return Starting index of /from flag.
     */
    private static int getFromFlagIndex(String userInput) {
        String fromSeparator = " /from ";
        return userInput.indexOf(fromSeparator);
    }

    //@@author Uxinnn
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /to flag.
     * @return Starting index of /to flag.
     */
    private static int getToFlagIndex(String userInput) {
        String toSeparator = " /to ";
        return userInput.indexOf(toSeparator);
    }

    //@@author Uxinnn
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /day flag.
     * @return Starting index of /day flag.
     */
    private static int getDayFlagIndex(String userInput) {
        String daySeparator = " /day ";
        return userInput.indexOf(daySeparator);
    }

    //@@author Uxinnn
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /time flag.
     * @return Starting index of /time flag.
     */
    private static int getTimeFlagIndex(String userInput) {
        String timeSeparator = " /time ";
        return userInput.indexOf(timeSeparator);
    }

    //@@author conradwee
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /name flag.
     * @return Starting index of /name flag.
     */
    private static int getNameFlagIndex(String userInput) {
        String nameSeparator = " /name ";
        return userInput.indexOf(nameSeparator);
    }

    //@@author conradwee
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /item flag.
     * @return Starting index of /item flag.
     */
    private static int getItemFlagIndex(String userInput) {
        String itemSeparator = " /item ";
        return userInput.indexOf(itemSeparator);
    }

    //@@author conradwee
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /key flag.
     * @return Starting index of /key flag.
     */
    private static int getKeyFlagIndex(String userInput) {
        String keywordSeparator = " /key ";
        return userInput.indexOf(keywordSeparator);
    }

    //@@author conradwee
    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /index flag.
     * @return Starting index of /index flag.
     */
    private static int getIndexFlagIndex(String userInput) {
        String indexSeparator = " /index ";
        return userInput.indexOf(indexSeparator);
    }
}

