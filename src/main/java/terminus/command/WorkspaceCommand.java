package terminus.command;

import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;
import terminus.parser.InModuleCommandParser;
import terminus.ui.Ui;

public abstract class WorkspaceCommand extends Command {

    protected CommandParser commandMap;
    private static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "%s %s";

    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }

    public WorkspaceCommand(InModuleCommandParser commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Returns the Command Result after execution. If no other arguments, returns the workspace.
     *
     * @param ui            The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the list of all notes and schedules.
     * @return The CommandResult containing success or failure of command and CommandParser Object.
     * @throws InvalidCommandException when the command could not be found.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        assert commandMap != null;
        TerminusLogger.info("Executing Workspace Command");
        if (isNotNullOrBlank()) {
            try {
                TerminusLogger.info("Parsing workspace command");
                return commandMap.parseCommand(arguments).execute(ui, moduleManager);
            } catch (InvalidArgumentException e) {
                if (e.getFormat() == null) {
                    throw e;
                }
                TerminusLogger.warning("Failed to parse command.");
                TerminusLogger.warning(commandMap.getWorkspace() + " : " + e.getFormat());
                throw new InvalidArgumentException(
                        String.format(INVALID_ARGUMENT_FORMAT_MESSAGE, commandMap.getWorkspace(), e.getFormat()),
                        e.getMessage()
                );
            }
        } else {
            TerminusLogger.info("Switching workspace to: " + commandMap.getWorkspace());
            return new CommandResult(true, commandMap);
        }
    }

    private boolean isNotNullOrBlank() {
        return arguments != null && !arguments.isBlank();
    }
}
