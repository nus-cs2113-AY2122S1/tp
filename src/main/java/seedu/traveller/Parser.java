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
import seedu.traveller.exceptions.IllegalTripNameException;
import seedu.traveller.exceptions.InvalidAddDayFormatException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidDeleteDayFormatException;
import seedu.traveller.exceptions.InvalidDeleteItemFormatCommand;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.InvalidNumberOfDaysException;
import seedu.traveller.exceptions.InvalidSearchFormatException;
import seedu.traveller.exceptions.InvalidEditMapFormatException;
import seedu.traveller.exceptions.CountryNotFoundException;
import seedu.traveller.exceptions.DistanceNonNegativeException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.InvalidShortestFormatException;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.WorldMap;
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
        Command command;
        String tripName;
        String itemName;
        String itemTime;
        String rawDayNumber;

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String timeSeparator = " /time ";
            int timeIdx = userInput.indexOf(timeSeparator);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, timeIdx);

            String nameSeparator = " /name ";
            int nameIdx = userInput.indexOf(nameSeparator);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);

            itemName = userInput.substring(nameIdx + NAME_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        assert dayIndex >= 0 : "Day index is negative.";

        command = new AddItemCommand(tripName, dayIndex, itemTime, itemName);

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
            String fromSeparator = " /from ";
            String toSeparator = " /to ";
            int fromIdx = userInput.indexOf(fromSeparator);
            int toIdx = userInput.indexOf(toSeparator);
            String tripName = userInput.substring(0, fromIdx);
            if (tripName.equals("all") || tripName.equals("")) {
                throw new IllegalTripNameException(tripName);
            }
            String startCountryCode = userInput.substring(fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();
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
            String fromSeparator = " /from ";
            String toSeparator = " /to ";
            int fromIdx = userInput.indexOf(fromSeparator);
            int toIdx = userInput.indexOf(toSeparator);
            String tripName = userInput.substring(0, fromIdx);
            String startCountryCode = userInput.substring(fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();
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
    private static Command parseDeleteCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Delete command input");
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
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);
            rawDayIndex = userInput.substring(dayIdx + DAY_LENGTH);
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
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String itemSeparator = " /item ";
            int itemIdx = userInput.indexOf(itemSeparator);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, itemIdx);

            rawItemNumber = userInput.substring(itemIdx + ITEM_LENGTH);
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

    private static Command parseSearchItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        String tripName;
        String rawDayNumber;
        String keyword;

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String keywordSeparator = " /key ";
            int keywordIdx = userInput.indexOf(keywordSeparator);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, keywordIdx);

            keyword = userInput.substring(keywordIdx + KEY_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        assert dayIndex >= 0 : "Day index is negative.";

        command = new SearchItemCommand(tripName, Integer.valueOf(dayIndex), keyword);

        return command;
    }

    private static Command parseEditItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-item command input");
        Command command;
        String tripName;
        String itemName;
        String itemTime;
        String itemIndex;
        String rawDayNumber;

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String timeSeparator = " /time ";
            int timeIdx = userInput.indexOf(timeSeparator);
            rawDayNumber = userInput.substring(dayIdx + DAY_LENGTH, timeIdx);

            String nameSeparator = " /name ";
            int nameIdx = userInput.indexOf(nameSeparator);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);

            String indexSeparator = " /index ";
            int indexIdx = userInput.indexOf(indexSeparator);
            itemName = userInput.substring(nameIdx + NAME_LENGTH, indexIdx);

            itemIndex = userInput.substring(indexIdx + INDEX_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }
        int dayIndex = parseValidIndex(rawDayNumber);
        assert dayIndex >= 0 : "Day index is negative.";

        command = new EditItemCommand(tripName, Integer.valueOf(dayIndex),
                itemTime, itemName, Integer.valueOf(itemIndex));

        return command;
    }

    /**
     * Parses user input to give a <code>ViewCommand</code>.
     * @return Command A <code>ViewCommand</code> object.
     */
    private static Command parseViewCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "View command input");
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
        try {
            String toSeparator = " /to ";
            int toIdx = userInput.indexOf(toSeparator);
            String startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            WorldMap.getCountry(startCountryCode);
            WorldMap.getCountry(endCountryCode);
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("dist", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestFormatException();
        } catch (EmptyVertexException e) {
            throw new CountryNotFoundException();
        }
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
        try {
            String toSeparator = " /to ";
            int toIdx = userInput.indexOf(toSeparator);
            String startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();

            WorldMap.getCountry(startCountryCode);
            WorldMap.getCountry(endCountryCode);
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";

            command = new ShortestCommand("cost", startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidShortestFormatException();
        } catch (EmptyVertexException e) {
            throw new CountryNotFoundException();
        }
        return command;
    }

    private static Command parseEditMapCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-map command input");
        Command command;
        try {
            String toSeparator = " /to ";
            String distSeparator = " /dist ";
            int toIdx = userInput.indexOf(toSeparator);
            int distIdx = userInput.indexOf(distSeparator);
            String startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH, distIdx).toUpperCase();
            double dist = Double.parseDouble(userInput.substring(distIdx + DIST_LENGTH));

            WorldMap.getCountry(startCountryCode);
            WorldMap.getCountry(endCountryCode);
            WorldMap.distanceNonZero(dist);

            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
            assert !(dist < 0.1) : "distance should be more than 0.1.";

            command = new EditMapCommand(startCountryCode, endCountryCode, dist);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEditMapFormatException();
        } catch (EmptyVertexException e) {
            throw new CountryNotFoundException();
        } catch (NonZeroDistanceException e) {
            throw new DistanceNonNegativeException();
        }
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
        String rawDaysIndex = "";

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);
            rawDaysIndex = userInput.substring(dayIdx + DAY_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid add-day command format: " + userInput);
            throw new InvalidAddDayFormatException();
        }
        int daysIndex = parseValidIndex(rawDaysIndex);
        assert daysIndex >= 0 : "Number of days is negative.";

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

    /**
     * Used to check if a user input value for the day/item field is valid.
     * @param rawIndex Raw day or item index as inputted by user.
     * @return Day or item index as an integer.
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
}

