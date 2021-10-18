package seedu.duke.command;

public class ByeCommand extends Command {

    private static final CommandEnum COMMAND = CommandEnum.BYE;

    public ByeCommand() {
        super(COMMAND);
    }

    private static final String EXIT_MSG = "Exiting program!";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MSG, false, true);
    }
}
