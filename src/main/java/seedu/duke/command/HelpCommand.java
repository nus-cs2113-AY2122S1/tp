package seedu.duke.command;

public class HelpCommand extends Command {

    private static final CommandEnum COMMAND = CommandEnum.HELP;
    private static final String HEADER = "List of commands: \n";

    public HelpCommand() {
        super(COMMAND);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = HEADER;
        for (CommandEnum command : CommandEnum.values()) {
            message += command.getUsage() + "\n";
        }
        return new CommandResult(message, false, false);
    }

}
