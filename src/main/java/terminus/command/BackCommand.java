package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.ModuleWorkspaceCommandParser;

public class BackCommand extends Command {

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_BACK;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_BACK;
    }

    /**
     * Returns a command result containing the CommandParser object.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult contains a CommandParser
     * @throws InvalidCommandException Throws if the command is invalid
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidCommandException {
        TerminusLogger.info("Executing Back Command");
        if (isModuleNameNullOrEmpty()) {
            MainCommandParser mainParser = MainCommandParser.getInstance();
            TerminusLogger.info("Changing Workspace to:" + mainParser.getWorkspace());
            return new CommandResult(mainParser);
        } else {
            ModuleWorkspaceCommandParser moduleParser = ModuleWorkspaceCommandParser.getInstance();
            moduleParser.setWorkspace(getModuleName());
            TerminusLogger.info("Changing Workspace to:" + moduleParser.getWorkspace());
            return new CommandResult(moduleParser);
        }
    }

    private boolean isModuleNameNullOrEmpty() {
        String moduleName = getModuleName();
        return moduleName == null || moduleName.isBlank();
    }
}
