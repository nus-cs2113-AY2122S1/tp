package seedu.duke.commands;

/**
 * The parser class contains methods to process the string input by the user.
 */
public class Parser {
    private static final String COMMAND_EXIT = "exit";

    /**
     * Sole constructor
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
        } else {
            return new UnknownCommand();
        }
    }
}
