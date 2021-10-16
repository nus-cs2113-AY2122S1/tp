package seedu.traveller;

import seedu.traveller.commands.additemcommands.AddDiningItemCommand;
import seedu.traveller.commands.additemcommands.AddHousingItemCommand;
import seedu.traveller.commands.AddTripDayCommand;
import seedu.traveller.commands.Command;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.SearchCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidItemTypeException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.InvalidSearchFormatException;
import seedu.traveller.exceptions.TravellerException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private static final int FROM_LENGTH = 7;
    private static final int TO_LENGTH = 5;
    private static final int DAY_LENGTH = 6;
    private static final int TYPE_LENGTH = 7;
    private static final int NAME_LENGTH = 7;
    private static final int DETAILS_LENGTH = 10;


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

    private static Command parseAddItemCommand(String userInput) throws TravellerException {
        logger.log(Level.INFO, "Add-item command input");
        Command command;
        String tripName;
        int dayIndex;
        String itemType;
        String itemName;
        String details;

        try {
            String daySeparator = " /day ";
            int dayIdx = userInput.indexOf(daySeparator);
            tripName = userInput.substring(0, dayIdx);

            String typeSeparator = " /type ";
            int typeIdx = userInput.indexOf(typeSeparator);
            dayIndex = Integer.parseInt(userInput.substring(dayIdx + DAY_LENGTH, typeIdx));

            String nameSeparator = " /name ";
            int nameIdx = userInput.indexOf(nameSeparator);
            itemType = userInput.substring(typeIdx + TYPE_LENGTH, nameIdx).toLowerCase();

            String detailsSeparator = " /details ";
            int detailsIdx = userInput.indexOf(detailsSeparator);
            itemName = userInput.substring(nameIdx + NAME_LENGTH, detailsIdx);

            details = userInput.substring(detailsIdx + DETAILS_LENGTH);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAddItemFormatException();
        }

        switch (itemType) {
        case ("housing"):
            command = new AddHousingItemCommand(tripName, dayIndex, itemName, details);
            break;
        case ("dining"):
            command = new AddDiningItemCommand(tripName, dayIndex, itemName, details);
            break;
        default:
            throw new InvalidItemTypeException(itemType);
        }
        return command;
    }

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

    private static Command parseDeleteCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Delete command input");
        command = new DeleteCommand(userInput);
        return command;
    }

    private static Command parseViewallCommand() {
        logger.log(Level.INFO, "Viewall command input");
        return new ViewAllCommand();
    }

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

    private static Command parseAddDayCommand(String userInput) {
        Command command;
        logger.log(Level.INFO, "Add-day command input");
        command = new AddTripDayCommand(userInput);
        return command;
    }

    private static Command parseExitCommand() {
        logger.log(Level.INFO, "Exit command input");
        Command command = new ExitCommand();
        return command;
    }
}