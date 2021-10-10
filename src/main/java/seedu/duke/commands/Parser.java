package seedu.duke.commands;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_ADD = "add";

    /**
     * Processes the commands inputted by the user.
     *
     * @param input A string corresponding to a specific command
     * @return The specific Command object corresponding to the input
     */
    public static Command parse(String input) {
        if (input.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        } else if (input.startsWith(COMMAND_ADD)) {
            String args = input.substring(COMMAND_ADD.length() + 1);
            return new AddCommand(args);
        } else {
            return new UnknownCommand();
        }
    }
}
