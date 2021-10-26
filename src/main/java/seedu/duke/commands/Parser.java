package seedu.duke.commands;

import static seedu.duke.commands.RemoveCommand.COMMAND_REMOVE;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {

    /**
     * Sole constructor.
     */
    public Parser() {
    }

    /**
     * Processes the commands inputted by the user.
     *
     * @param input A string corresponding to a specific command
     * @return The specific Command object corresponding to the input
     */
    public Command parse(String input) {
        if (input.equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        } else if (input.startsWith(COMMAND_REMOVE)) {
            return new RemoveCommand(input);
        } else if (input.startsWith(ListCommand.COMMAND_WORD)) {
            return new ListCommand(input);
        } else if (input.startsWith(AddCommand.COMMAND_WORD)) {
            return new AddCommand(input);
        } else if (input.startsWith(LoanCommand.COMMAND_WORD)) {
            return new LoanCommand(input);
        } else if (input.startsWith(ReturnCommand.COMMAND_WORD)) {
            return new ReturnCommand(input);
        } else if (input.startsWith(EditCommand.COMMAND_WORD)) {
            return new EditCommand(input);
        } else if (input.startsWith(ReserveCommand.COMMAND_WORD)) {
            return new ReserveCommand(input);
        } else {
            return new UnknownCommand();
        }
    }
}
