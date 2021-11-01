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
import seedu.traveller.commands.EditMapCommand;
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
import seedu.traveller.exceptions.InvalidEditMapFormatException;
import seedu.traveller.exceptions.DistanceNonNegativeException;
import seedu.traveller.exceptions.DistanceNonStringException;
import seedu.traveller.exceptions.InvalidSearchItemFormatException;
import seedu.traveller.exceptions.InvalidEditItemIndexException;
import seedu.traveller.exceptions.InvalidEditItemFormatException;
import seedu.traveller.exceptions.InvalidShortestFormatException;
import seedu.traveller.exceptions.InvalidViewCommandException;
import seedu.traveller.exceptions.TravellerException;

import seedu.traveller.worldmap.WorldMap;
import seedu.traveller.worldmap.exceptions.NonStringDistanceException;
import seedu.traveller.worldmap.exceptions.NonZeroDistanceException;

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
    private static final int DIST_LENGTH = 7;


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
            case "edit":
                command = parseEditCommand(userInput[1]);
                break;
            case "delete":
                command = parseDeleteCommand(userInput[1]);
                break;
            case "view":
                command = parseViewCommand(userInput[1]);
                break;
            case "shortest-dist":
                command = parseShortestDistCommand(userInput[1]);
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
            case "delete-day":
                command = parseDeleteDayCommand(userInput[1]);
                break;
            case "delete-item":
                command = parseDeleteItemCommand(userInput[1]);
                break;
            case "edit-item":
                command = parseEditItemCommand(userInput[1]);
                break;
            case "exit":
                command = parseExitCommand();
                break;
            case "help":
                command = parseHelpCommand();
                break;
            case "edit-map":
                command = parseEditMapCommand(userInput[1]);
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

            tripName = userInput.substring(0, dayIdx);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, timeIdx);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);
            itemName = userInput.substring(nameIdx + NAME_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        parseValidFieldValue(new String[] {tripName, rawDayNumber, itemTime, itemName});
        parseValidTime(itemTime);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command = new AddItemCommand(tripName, dayIndex, itemTime, itemName);

        return command;
    }

    /**
     * Parses user input to give a <code>NewCommand</code>.
     * @param userInput Raw user input, with the first command option (new) removed.
     * @return Command A <code>NewCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseNewCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "New command input");
        Command command;
        try {
            int fromIdx = getFromFlagIndex(userInput);
            int toIdx = getToFlagIndex(userInput);

            String tripName = userInput.substring(0, fromIdx);
            String startCountryCode = userInput.substring(fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            parseValidFieldValue(new String[] {tripName, startCountryCode, endCountryCode});
            parseValidTripName(tripName);

            command = new NewCommand(tripName, startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid new command format: " + userInput);
            throw new InvalidNewFormatException();
        }
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
            int fromIdx = getFromFlagIndex(userInput);
            int toIdx = getToFlagIndex(userInput);

            String tripName = userInput.substring(0, fromIdx);
            String startCountryCode = userInput.substring(fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            parseValidFieldValue(new String[] {tripName, startCountryCode, endCountryCode});
            parseValidTripName(tripName);

            command = new EditCommand(tripName, startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditFormatException();
        }
        return command;
    }

    /**
     * Parses user input to give a <code>DeleteCommand</code>.
     * @param userInput Raw user input, with the first command option (delete) removed.
     * @return Command A <code>DeleteCommand</code> object.
     */
    private static Command parseDeleteCommand(String userInput) throws TravellerException {
        Command command;
        logger.log(Level.INFO, "Delete command input");
        parseValidFieldValue(new String[] {userInput});
        command = new DeleteCommand(userInput);
        return command;
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

            tripName = userInput.substring(0, dayIdx);
            rawDayIndex = userInput.substring(dayIdx + DAY_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid delete-day command format: " + userInput);
            throw new InvalidDeleteDayFormatException();
        }
        parseValidFieldValue(new String[] {tripName, rawDayIndex});
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

            tripName = userInput.substring(0, dayIdx);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, itemIdx);
            rawItemNumber = userInput.substring(itemIdx + ITEM_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidDeleteItemFormatCommand();
        }
        parseValidFieldValue(new String[] {tripName, rawDayNumber, rawItemNumber});

        int dayNumber = parseValidIndex(rawDayNumber);
        assert dayNumber >= 0 : "Day number is negative.";

        int itemNumber = parseValidIndex(rawItemNumber);
        assert itemNumber >= 0 : "Item number is negative.";

        Command command;
        command = new DeleteItemCommand(tripName, dayNumber, itemNumber);
        return command;
    }

    private static Command parseSearchItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        String tripName;
        String rawDayNumber;
        String keyword;

        try {
            int dayIdx = getDayFlagIndex(userInput);
            int keywordIdx = getKeyFlagIndex(userInput);

            tripName = userInput.substring(0, dayIdx);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, keywordIdx);
            keyword = userInput.substring(keywordIdx + KEY_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSearchItemFormatException();
        }
        parseValidFieldValue(new String[] {tripName, rawDayNumber, keyword});
        int dayIndex = parseValidIndex(rawDayNumber);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command = new SearchItemCommand(tripName, dayIndex, keyword);

        return command;
    }

    private static Command parseEditItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-item command input");
        String tripName;
        String itemName;
        String itemTime;
        int itemIndex;
        String rawIndex;
        String rawDayNumber;

        try {
            int dayIdx = getDayFlagIndex(userInput);
            tripName = userInput.substring(0, dayIdx);

            int timeIdx = getTimeFlagIndex(userInput);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, timeIdx);

            int nameIdx = getNameFlagIndex(userInput);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);

            int indexIdx = getIndexFlagIndex(userInput);
            itemName = userInput.substring(nameIdx + NAME_LENGTH, indexIdx);

            rawIndex = userInput.substring(indexIdx + INDEX_LENGTH);

            try {
                itemIndex = Integer.parseInt(rawIndex);
            } catch (NumberFormatException e) {
                throw new InvalidEditItemIndexException();
            }

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditItemFormatException();
        }
        parseValidFieldValue(new String[] {tripName, rawDayNumber, itemTime, itemName, rawIndex});
        int dayIndex = parseValidIndex(rawDayNumber);
        parseValidTime(itemTime);
        assert dayIndex >= 0 : "Day index is negative.";

        Command command = new EditItemCommand(tripName, dayIndex,
                itemTime, itemName, itemIndex);

        return command;
    }

    /**
     * Parses user input to give a <code>ViewCommand</code>.
     * @return Command A <code>ViewCommand</code> object.
     */
    private static Command parseViewCommand(String userInput) throws  TravellerException {
        Command command;
        logger.log(Level.INFO, "View command input");
        parseValidFieldValue(new String[] {userInput});
        command = new ViewCommand(userInput);
        return command;
    }

    /**
     * Parses user input to give a <code>ShortestCommand</code>.
     * @param userInput Raw user input, with the first command option (search) removed.
     * @return Command A <code>ShortestCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseShortestDistCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        String startCountryCode;
        String endCountryCode;
        try {
            int toIdx = getToFlagIndex(userInput);
            startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("dist", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestFormatException();
        }
        parseValidFieldValue(new String[] {startCountryCode, endCountryCode});

        return command;
    }

    /**
     * Parses user input to give a <code>ShortestCommand</code>.
     * @param userInput Raw user input, with the first command option (search) removed.
     * @return Command A <code>ShortestCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseShortestCostCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        String startCountryCode;
        String endCountryCode;
        try {
            int toIdx = getToFlagIndex(userInput);
            startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("cost", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestFormatException();
        }
        parseValidFieldValue(new String[] {startCountryCode, endCountryCode});

        return command;
    }

    private static Command parseEditMapCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-map command input");
        Command command;
        String startCountryCode;
        String endCountryCode;
        String rawDist;

        try {
            int distIdx = getDistFlagIndex(userInput);

            rawDist = userInput.substring(distIdx + DIST_LENGTH);

            int toIdx = getToFlagIndex(userInput);

            try {
                WorldMap.distanceNonString(rawDist);
            } catch (NonStringDistanceException e) {
                throw new DistanceNonStringException();
            }

            double dist = Double.parseDouble(rawDist);
            WorldMap.distanceNonZero(dist);

            startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            endCountryCode = userInput.substring(toIdx + TO_LENGTH, distIdx).toUpperCase();
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
            assert !(dist < 0.1) : "distance should be more than 0.1.";

            command = new EditMapCommand(startCountryCode, endCountryCode, dist);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditMapFormatException();
        } catch (NonZeroDistanceException e) {
            throw new DistanceNonNegativeException();
        }
        parseValidFieldValue(new String[] {startCountryCode, endCountryCode, rawDist});

        return command;
    }

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
            tripName = userInput.substring(0, dayIdx);
            rawDaysIndex = userInput.substring(dayIdx + DAY_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid add-day command format: " + userInput);
            throw new InvalidAddDayFormatException();
        }
        parseValidFieldValue(new String[] {tripName, rawDaysIndex});

        int daysIndex = parseValidIndex(rawDaysIndex);
        if (daysIndex == 0) {
            throw new InvalidNumberOfDaysException(daysIndex);
        }
        assert daysIndex > 0 : "Number of days is negative or 0.";

        Command command;
        command = new AddDayCommand(tripName, daysIndex);
        return command;
    }

    /**
     * Parses user input to give an <code>ExitCommand</code>.
     * @return Command An <code>ExitCommand</code> object.
     */
    private static Command parseExitCommand() {
        logger.log(Level.INFO, "Exit command input");
        return new ExitCommand();
    }

    /**
     * Launches help menu.
     * @return Command An <code>ExitCommand</code> object.
     */
    private static Command parseHelpCommand() {
        logger.log(Level.INFO, "Help command input");
        return new HelpCommand();
    }

    private static void parseValidFieldValue(String[] arrayOfValues) throws TravellerException {
        for (String value : arrayOfValues) {
            if (Objects.equals(value, "")) {
                throw new EmptyFieldValueException();
            }
        }
    }

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

    /**
     * Used to check if a user input value for the tripName field is valid.
     * @param tripName TripName that user has entered.
     * @throws TravellerException If <code>TripName</code> is not a valid <code>TripName</code>.
     */
    private static void parseValidTripName(String tripName) throws TravellerException {
        if (tripName.equals("all")) {
            throw new IllegalTripNameException(tripName);
        }
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /from flag.
     * @return Starting index of /from flag.
     */
    private static int getFromFlagIndex(String userInput) {
        String fromSeparator = " /from ";
        return userInput.indexOf(fromSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /to flag.
     * @return Starting index of /to flag.
     */
    private static int getToFlagIndex(String userInput) {
        String toSeparator = " /to ";
        return userInput.indexOf(toSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /day flag.
     * @return Starting index of /day flag.
     */
    private static int getDayFlagIndex(String userInput) {
        String daySeparator = " /day ";
        return userInput.indexOf(daySeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /time flag.
     * @return Starting index of /time flag.
     */
    private static int getTimeFlagIndex(String userInput) {
        String timeSeparator = " /time ";
        return userInput.indexOf(timeSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /name flag.
     * @return Starting index of /name flag.
     */
    private static int getNameFlagIndex(String userInput) {
        String nameSeparator = " /name ";
        return userInput.indexOf(nameSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /item flag.
     * @return Starting index of /item flag.
     */
    private static int getItemFlagIndex(String userInput) {
        String itemSeparator = " /item ";
        return userInput.indexOf(itemSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /key flag.
     * @return Starting index of /key flag.
     */
    private static int getKeyFlagIndex(String userInput) {
        String keywordSeparator = " /key ";
        return userInput.indexOf(keywordSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /index flag.
     * @return Starting index of /index flag.
     */
    private static int getIndexFlagIndex(String userInput) {
        String indexSeparator = " /index ";
        return userInput.indexOf(indexSeparator);
    }

    /**
     * Used to parse flags in raw user input.
     * @param userInput Input string which contains /dist flag.
     * @return Starting index of /dist flag.
     */
    private static int getDistFlagIndex(String userInput) {
        String distSeparator = " /dist ";
        return userInput.indexOf(distSeparator);
    }
}

