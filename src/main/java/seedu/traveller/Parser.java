package seedu.traveller;

import seedu.traveller.commands.AddDayCommand;
import seedu.traveller.commands.AddItemCommand;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.SearchCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.commands.Command;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.InvalidSearchFormatException;
import seedu.traveller.exceptions.TravellerException;

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
    private static final int TIME_LENGTH = 7;


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
        case "viewall":
            command = parseViewallCommand();
            break;
        case "search":
            command = parseSearchCommand(userInput[1]);
            break;
        case "add-day":
            command = parseAddDayCommand(userInput[1]);
            break;
        case "add-item":
            command = parseAddItemCommand(userInput[1]);
            break;
        case "exit":
            command = parseExitCommand();
            break;
        default:
            logger.log(Level.WARNING, "Invalid command input!");
            throw new CommandNotFoundException(rawInput);
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
        int dayIndex;
        String itemName;
        String itemTime;

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String timeSeparator = " /time ";
            int timeIdx = userInput.indexOf(timeSeparator);
            dayIndex = Integer.parseInt(userInput.substring(dayIdx + DAY_LENGTH, timeIdx));

            String nameSeparator = " /name ";
            int nameIdx = userInput.indexOf(nameSeparator);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);

            itemName = userInput.substring(nameIdx + NAME_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }

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
            String startCountryCode = userInput.substring(fromIdx + FROM_LENGTH, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
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
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
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
     * Parses user input to give a <code>ViewallCommand</code>.
     * @return Command A <code>ViewallCommand</code> object.
     */
    private static Command parseViewallCommand() {
        logger.log(Level.INFO, "Viewall command input");
        return new ViewAllCommand();
    }

    /**
     * Parses user input to give a <code>SearchCommand</code>.
     * @param userInput Raw user input, with the first command option (search) removed.
     * @return Command A <code>SearchCommand</code> object.
     * @throws TravellerException Will be thrown if the user input cannot be understood.
     */
    private static Command parseSearchCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        try {
            String toSeparator = " /to ";
            int toIdx = userInput.indexOf(toSeparator);
            String startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
            command = new SearchCommand(startCountryCode, endCountryCode);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSearchFormatException();
        }
        return command;
    }

    /**
     * Parses user input to give an <code>AddDayCommand</code>.
     * @param userInput Raw user input, with the first command option (add-day) removed.
     * @return Command An <code>AddDayCommand</code> object.
     */
    private static Command parseAddDayCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Add-day command input");
        command = new AddDayCommand(userInput);
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
}