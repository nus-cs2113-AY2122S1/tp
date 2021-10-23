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
import seedu.traveller.exceptions.IllegalTripNameException;
import seedu.traveller.exceptions.InvalidAddDayFormatException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidFormatException;
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
            case "shortest":
                command = parseShortestCommand(userInput[1]);
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
            if (tripName.equals("all")) {
                throw new IllegalTripNameException(tripName);
            }
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
     * Parses user input to give a <code>DeleteDayCommand</code>.
     * @param userInput Raw user input, with the first command option (delete-day) removed.
     * @return Command A <code>DeleteDayCommand</code> object.
     */
    private static Command parseDeleteDayCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Delete command input");
        String[] input = userInput.split(" ");
        command = new DeleteDayCommand(input[0],Integer.valueOf(input[1]));
        return command;
    }

    /**
     * Parses user input to give a <code>DeleteItemCommand</code>.
     * @param userInput Raw user input, with the first command option (delete-item) removed.
     * @return A <code>DeleteItemCommand</code> object.
     */
    private static Command parseDeleteItemCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Delete command input");
        String[] input = userInput.split(" ");
        command = new DeleteItemCommand(input[0],Integer.valueOf(input[1]),Integer.valueOf(input[2]));
        return command;
    }

    private static Command parseSearchItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");

        String[] input = userInput.split(" ");

        String tripName;
        String itemName;

        tripName = input[1];
        String nameSeparator = " /name ";
        int nameIdx = userInput.indexOf(nameSeparator);
        itemName = userInput.substring(nameIdx + NAME_LENGTH);

        Command command;
        command = new SearchItemCommand(tripName, itemName);

        return command;
    }

    private static Command parseEditItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Edit-item command input");
        Command command;
        String[] input = userInput.split(" ");

        String tripName;
        String itemName;
        String itemTime;
        int itemIndex;

        try {
            tripName = input[1];
            itemIndex = Integer.valueOf(input[0]);

            String nameSeparator = " /name ";
            int nameIdx = userInput.indexOf(nameSeparator);
            itemName = userInput.substring(nameIdx + NAME_LENGTH);

            String timeSeparator = " /time ";
            int timeIdx = userInput.indexOf(timeSeparator);
            itemTime = userInput.substring(timeIdx + TIME_LENGTH, nameIdx);

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }

        command = new EditItemCommand(tripName, itemIndex, itemTime, itemName);

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
    private static Command parseShortestCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Search command input");
        Command command;
        try {
            String toSeparator = " /to ";
            int toIdx = userInput.indexOf(toSeparator);
            String startCountryCode = userInput.substring(FROM_LENGTH - 1, toIdx).toUpperCase();
            String endCountryCode = userInput.substring(toIdx + TO_LENGTH).toUpperCase();
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
            command = new ShortestCommand(startCountryCode, endCountryCode);
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
    private static Command parseAddDayCommand(String userInput) throws TravellerException {
        String[] rawInputs = userInput.split(" ", 2);
        String tripName = rawInputs[0];
        int daysNumber;
        try {
            daysNumber = Integer.parseInt(rawInputs[1]);
        } catch (NumberFormatException e) {
            throw new InvalidAddDayFormatException();
        }
        Command command;
        logger.log(Level.INFO, "Add-day command input");
        command = new AddDayCommand(tripName, daysNumber);
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
}

