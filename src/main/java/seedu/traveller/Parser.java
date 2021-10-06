package seedu.traveller;

import seedu.traveller.commands.Command;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewAllCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.TravellerException;

public class Parser {
    public static Command parse(String rawInput) throws TravellerException {
        String details;
        Command command;

        String[] userInput = rawInput.split(" ", 2);
        String userCommand = userInput[0].toLowerCase();

        switch(userCommand) {
            case "new":
                try {
                    String tripName = userInput[1];
                    command = new NewCommand(tripName);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidNewFormatException();
                }
                break;
            case "viewall":
                command = new ViewAllCommand();
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
