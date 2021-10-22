package seedu.duke.command;

public class InvalidCommand extends Command {
    private static final CommandEnum COMMAND = CommandEnum.INVALID;

    static final String INVALID_MSG = "The command you returned is not understood";

    public InvalidCommand() {
        super(COMMAND);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        return new CommandResult(INVALID_MSG, false, false);
    }
}
