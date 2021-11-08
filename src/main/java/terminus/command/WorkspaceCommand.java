package terminus.command;

import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;

public abstract class WorkspaceCommand extends Command {

    protected CommandParser commandMap;


    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        this.arguments = arguments;
    }

    /**
     * Returns the Command Result after execution. If no other arguments, returns the workspace.
     *
     * @param moduleManager The NusModule contain the list of all notes and schedules.
     * @return The CommandResult containing success or failure of command and CommandParser Object.
     * @throws InvalidCommandException when the command could not be found.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        assert commandMap != null;
        TerminusLogger.info("Executing Workspace Command");
        if (isNotNullOrBlank()) {
            TerminusLogger.info("Parsing workspace command");
            return commandMap.parseCommand(arguments).execute(moduleManager);
        } else {
            TerminusLogger.info("Switching workspace to: " + commandMap.getWorkspace());
            return new CommandResult(commandMap);
        }
    }

    private boolean isNotNullOrBlank() {
        return arguments != null && !arguments.isBlank();
    }
}
