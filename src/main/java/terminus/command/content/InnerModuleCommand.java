package terminus.command.content;

import java.io.IOException;
import terminus.command.CommandResult;
import terminus.command.WorkspaceCommand;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.InnerModuleCommandParser;

public abstract class InnerModuleCommand extends WorkspaceCommand {

    private InnerModuleCommandParser commandMap;

    public InnerModuleCommand(InnerModuleCommandParser commandMap) {
        super(commandMap);
        this.commandMap = commandMap;
    }

    @Override
    public CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        commandMap.setModuleName(getModuleName());
        try {
            return super.execute(moduleManager);
        } catch (InvalidArgumentException e) {
            if (e.getFormat() == null) {
                throw e;
            }
            TerminusLogger.warning("Failed to parse command.");
            TerminusLogger.warning(commandMap.getWorkspace() + " : " + e.getFormat());
            throw new InvalidArgumentException(
                    String.format(Messages.INVALID_ARGUMENT_FORMAT_MESSAGE_EXCEPTION, 
                        commandMap.getWorkspace(true), e.getFormat()),
                e.getMessage()
            );
        }

    }
}
