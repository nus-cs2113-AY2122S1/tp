package seedu.duke.logic.commands;

/**
 * Represents the command that when executed, signaCommandMessages.LS to the application to exit.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private static final String MESSAGE_SUCCESS = "Exiting Fitbot...." + CommandMessages.LS
            + "Bye! Hope to see you again soon!!";


    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(MESSAGE_SUCCESS);
        result.setBye(true);
        return result;
    }

    /**
     * Determines if command is the Bye command so that the main program knows when to exit.
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
