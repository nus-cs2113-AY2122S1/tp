package seedu.duke.parser;

import seedu.duke.command.ExitCommand;
import seedu.duke.exception.GetJackDException;

/**
 * To execute task actions based on keywords used in user command.
 */
public class TaskManager {
    /**
     * Processes the extracted user command.
     *
     * @param command is the extracted first word of the user input
     * @param input   is the command given by the user
     * @throws GetJackDException if invalid command is given
     */
    public static void parseAndExecuteUserCommand(String command, String input) throws GetJackDException {
        switch (command) {
        case "bye":
            // Changes the loop condition to true to exit from the program
            ExitCommand.isExit();
            break;
        default:
            throw new GetJackDException("Invalid Command!");
        }
    }
}
