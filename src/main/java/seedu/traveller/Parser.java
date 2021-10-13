package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.commands.SearchCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidEditFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.TravellerException;


public class Parser {
    public static Command parse(String rawInput) throws TravellerException {
        Command command;

        String[] userInput = rawInput.split(" ", 5);
        String userCommand = userInput[0].toLowerCase();

        switch (userCommand) {
        case "new":
            try {
                String tripName = userInput[1];  // TODO: Is there a better way to do this?
                String startCountryCode = userInput[2];
                String endCountryCode = userInput[3];
                command = new NewCommand(tripName, startCountryCode, endCountryCode);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidNewFormatException();
            }
            break;
        case "edit":
            try {
                String tripName = userInput[1];
                String startCountryCode = userInput[2];
                String endCountryCode = userInput[3];
                command = new EditCommand(tripName, startCountryCode, endCountryCode);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidEditFormatException();
            }
            break;
        case "delete":
            String countryCode = userInput[1];
            command = new DeleteCommand(countryCode);
            break;
        case "viewall":
            command = new ViewAllCommand();
            break;
        case "search":
            String startCountryCode = userInput[1];
            String endCountryCode = userInput[2];
            command = new SearchCommand(startCountryCode, endCountryCode);
            break;
        case "exit":
            command = new ExitCommand();
            break;
        default:
            throw new CommandNotFoundException(rawInput);
        }
        return command;
    }
}