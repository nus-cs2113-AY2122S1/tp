package seedu.duke;

public class ExitCommand extends Command {
    public static final String MESSAGE = "bye";

    /**
     * Executes the Exit command.
     *
     * @return the result of exit command includes the message shown to user.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE);
    }

    /**
     * Return true or false whether to exit the programme.
     *
     * @param command the exit command as user input
     * @return true or false that exit the programme.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
