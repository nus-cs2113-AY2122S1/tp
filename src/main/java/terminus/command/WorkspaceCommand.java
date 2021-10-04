package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class WorkspaceCommand extends Command {

    protected String arguments;
    protected CommandParser commandMap;

    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void parseArguments(String arguments) {
        this.arguments = arguments;
    }

    /**
     * A custom execute for command that switch workplaces.
     * Runs any additional commands in arguments but will change works space if none is specified
     *
     * @param ui The Ui object to send messages to the users.
     * @param module The NusModule contain the list of all notes and schedules.
     * @return The CommandResult containing success or failure of command,
     * may include CommandParser for additional data
     * @throws InvalidCommandException Exception for when the command could not be found.
     */
    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        if (arguments != null && !arguments.isBlank()) {
            return commandMap.parseCommand(arguments).execute(ui, module);
        } else {
            return new CommandResult(true, commandMap);
        }
    }
}
