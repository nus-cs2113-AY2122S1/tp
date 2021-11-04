package seedu.duke.command;

public class ByeCommand extends Command {

    private static final String EXIT_MSG = "Exiting program!";
    private static final String USAGE = "-> Exiting the program: bye";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MSG, true);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }
}
