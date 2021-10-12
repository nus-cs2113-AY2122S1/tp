package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class WorkspaceCommand extends Command {

    protected CommandParser commandMap;
    private static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "%s %s";

    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
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
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException, InvalidArgumentException {
        assert commandMap != null;
        if (arguments != null && !arguments.isBlank()) {
            try {
                return commandMap.parseCommand(arguments).execute(ui, module);
            } catch (InvalidArgumentException e) {
                if (e.getFormat() == null) {
                    throw e;
                }
                throw new InvalidArgumentException(
                    String.format(INVALID_ARGUMENT_FORMAT_MESSAGE, commandMap.getWorkspace(), e.getFormat()),
                    e.getMessage()
                );
            }
        } else {
            return new CommandResult(true, commandMap);
        }
    }
}
