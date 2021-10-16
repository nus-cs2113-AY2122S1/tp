package seedu.traveller;

import seedu.traveller.commands.*;  // TODO: CHANGE!!
import seedu.traveller.commands.additemcommands.AddDiningItemCommand;
import seedu.traveller.commands.additemcommands.AddHousingItemCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.TravellerException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private static final int FROM_LENGTH = 7;
    private static final int TO_LENGTH = 5;


    public static Command parse(String rawInput) throws TravellerException {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Parsing raw user input");
        Command command;

        String[] userInput = rawInput.split(" ", 0);
        String userCommand = userInput[0].toLowerCase();

        switch (userCommand) {
        case "new":
            logger.log(Level.INFO, "New command input");
            try {
                String tripName = userInput[1];  // TODO: Is there a better way to do this?
                String startCountryCode = userInput[2].toUpperCase();
                String endCountryCode = userInput[3].toUpperCase();
                assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
                assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
                command = new NewCommand(tripName, startCountryCode, endCountryCode);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidNewFormatException();
            }
            break;
        case "edit":
            logger.log(Level.INFO, "Edit command input");
            try {
                String tripName = userInput[1];
                String startCountryCode = userInput[2].toUpperCase();
                String endCountryCode = userInput[3].toUpperCase();
                assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
                assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
                command = new EditCommand(tripName, startCountryCode, endCountryCode);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidEditFormatException();
            }
            break;
        case "delete":
            logger.log(Level.INFO, "Delete command input");
            String tripName = userInput[1];
            command = new DeleteCommand(tripName);
            break;
        case "viewall":
            logger.log(Level.INFO, "Viewall command input");
            command = new ViewAllCommand();
            break;
        case "search":
            logger.log(Level.INFO, "Search command input");
            String startCountryCode = userInput[1].toUpperCase();
            String endCountryCode = userInput[2].toUpperCase();
            assert !startCountryCode.contains(" ") : "startCountryCode should not contain whitespaces.";
            assert !endCountryCode.contains(" ") : "endCountryCode should not contain whitespaces.";
            command = new SearchCommand(startCountryCode, endCountryCode);
            break;
        case "add-day":
            logger.log(Level.INFO, "Add-day command input");
            tripName = userInput[1];
            command = new AddTripDayCommand(tripName);
            break;
        case "add-item":
            command = parseAddItem(userInput);
            break;
        case "exit":
            logger.log(Level.INFO, "Exit command input");
            command = new ExitCommand();
            break;
        default:
            logger.log(Level.WARNING, "Invalid command input!");
            throw new CommandNotFoundException(rawInput);
        }
        return command;
    }

    protected static Command parseAddItem(String[] userInput) {
        Command command = null;

        logger.log(Level.INFO, "Add-item command input");
        String tripName = userInput[1];
        int dayIndex = Integer.parseInt(userInput[2]);
        String itemType = userInput[3];
        String itemName = userInput[4];
        String details = userInput[5];
        switch (itemType) {
        case("housing"):
            command = new AddHousingItemCommand(tripName, dayIndex, itemName, details);
            break;
        case("dining"):
            command = new AddDiningItemCommand(tripName, dayIndex, itemName, details);
            break;
        }
        return command;
    }
}