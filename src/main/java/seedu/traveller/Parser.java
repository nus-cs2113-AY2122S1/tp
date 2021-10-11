package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.commands.SearchCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidFormatException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldMap.Logic;
import seedu.traveller.worldMap.WorldMap;
import seedu.traveller.worldMap.exceptions.EmptyVertexException;
import seedu.traveller.worldMap.GraphList;
import seedu.traveller.worldMap.Country;

import java.util.List;

public class Parser {
    public static Command parse(String rawInput) throws TravellerException {
        String details;
        Command command = null;

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
                throw new InvalidFormatException(userCommand);
            }
            break;
        case "edit":
            try {
                String tripName = userInput[1];
                String startCountryCode = userInput[2];
                String endCountryCode = userInput[3];
                command = new EditCommand(tripName, startCountryCode, endCountryCode);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidFormatException(userCommand);
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
            throw new CommandNotFoundException(userCommand);
        }
        return command;
    }
}