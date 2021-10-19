package terminus.command;

import java.io.IOException;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class WorkspaceCommand extends Command {

    protected CommandParser commandMap;


    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }


    /**
     * Returns the Command Result after execution. If no other arguments, returns the workspace.
     *
     * @param ui The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the list of all notes and schedules.
     * @return The CommandResult containing success or failure of command and CommandParser Object.
     * @throws InvalidCommandException when the command could not be found.
     * @throws IOException when the file to be saved is inaccessible (e.g. file is locked by OS).
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        assert commandMap != null;
        TerminusLogger.info("Executing Workspace Command");
        if (isNotNullOrBlank()) {
            TerminusLogger.info("Parsing workspace command");
            return commandMap.parseCommand(arguments).execute(ui, moduleManager);
        } else {
            TerminusLogger.info("Switching workspace to: " + commandMap.getWorkspace());
            return new CommandResult(true, commandMap);
        }
    }

    private boolean isNotNullOrBlank() {
        return arguments != null && !arguments.isBlank();
    }
}
