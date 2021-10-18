package terminus.command.content;

import terminus.command.CommandResult;
import terminus.command.WorkspaceCommand;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.InnerModuleCommandParser;
import terminus.ui.Ui;

public abstract class InnerModuleCommand extends WorkspaceCommand {

    private InnerModuleCommandParser commandMap;
    private static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "%s %s";

    public InnerModuleCommand(InnerModuleCommandParser commandMap) {
        super(commandMap);
        this.commandMap = commandMap;
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        commandMap.setModuleName(getModuleName());
        try {
            return super.execute(ui, moduleManager);
        } catch (InvalidArgumentException e) {
            if (e.getFormat() == null) {
                throw e;
            }
            TerminusLogger.warning("Failed to parse command.");
            TerminusLogger.warning(commandMap.getWorkspace() + " : " + e.getFormat());
            throw new InvalidArgumentException(
                    String.format(INVALID_ARGUMENT_FORMAT_MESSAGE,commandMap.getWorkspace(true), e.getFormat()),
                    e.getMessage()
            );
        }

    }
}
