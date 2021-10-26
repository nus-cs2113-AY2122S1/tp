package seedu.duke.command;

public class InvalidCommand extends Command {

    static final String INVALID_MSG = "The command you returned is not understood";

    @Override
    public CommandResult executeCommand() throws Exception {
        return new CommandResult(INVALID_MSG, false, false);
    }

    @Override
    protected String getUsage() {
        return null;
    }
}
