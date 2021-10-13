package seedu.duke.commands;

import static seedu.duke.commands.RemoveCommand.COMMAND_REMOVE;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_LIST_ALL = "list";
    private static final String COMMAND_LIST_AVAILABLE = "list available";
    private static final String COMMAND_LIST_LOANED = "list loaned";
    public static final String COMMAND_ADD = "add";
    private static final String COMMAND_LOAN = "loan";
    private static final String COMMAND_RETURN = "return";

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
        if (input.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        } else if (input.startsWith(COMMAND_REMOVE)) {
            return new RemoveCommand(input);
        } else if (input.equals(COMMAND_LIST_ALL)) {
            return new ListAllCommand();
        } else if (input.equals(COMMAND_LIST_AVAILABLE)) {
            return new ListAvailableCommand();
        } else if (input.equals(COMMAND_LIST_LOANED)) {
            return new ListLoanedCommand();
        } else if (input.startsWith(COMMAND_ADD)) {
            return new AddCommand(input);
        } else if (input.startsWith(COMMAND_LOAN)) {
            return new LoanCommand(input);
        } else if (input.startsWith(COMMAND_RETURN)) {
            return new ReturnCommand(input);
        } else {
            return new UnknownCommand();
        }
    }
}
