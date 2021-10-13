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
        } else if (input.equals(ListAllCommand.COMMAND_WORD)) {
            return new ListAllCommand();
        } else if (input.equals(ListAvailableCommand.COMMAND_WORD)) {
            return new ListAvailableCommand();
        } else if (input.equals(ListLoanedCommand.COMMAND_WORD)) {
            return new ListLoanedCommand();
        } else if (input.startsWith(AddCommand.COMMAND_WORD)) {
            return new AddCommand(input);
        } else if (input.startsWith(LoanCommand.COMMAND_WORD)) {
            return new LoanCommand(input);
        } else if (input.startsWith(ReturnCommand.COMMAND_WORD)) {
            return new ReturnCommand(input);
        } else {
            return new UnknownCommand();
        }
    }
}
