package terminus.command;

import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
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
     * Returns the Command Result after execution.
     * If no other arguments, returns the workspace.
     *
     * @param ui     The Ui object to send messages to the users.
     * @param module The NusModule contain the list of all notes and schedules.
     * @return The CommandResult containing success or failure of command and CommandParser Object
     * @throws InvalidCommandException Exception for when the command could not be found.
     */
    @Override
    public CommandResult execute(Ui ui, NusModule module)
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        if (arguments != null && !arguments.isBlank()) {
            return commandMap.parseCommand(arguments).execute(ui, module);
        } else {
            return new CommandResult(true, commandMap);
        }
    }
}
