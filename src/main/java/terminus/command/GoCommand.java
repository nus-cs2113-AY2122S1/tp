package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.parser.ModuleWorkspaceCommandParser;
import terminus.ui.Ui;

public class GoCommand extends WorkspaceCommand {

    private String moduleName;

    public GoCommand() {
        super(ModuleWorkspaceCommandParser.getInstance());
    }

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return "go <module name>";
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return "Go to a specific module's workspace";
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        String[] args = arguments.strip().split(CommonFormat.SPACE_DELIMITER, 2);
        moduleName = args[0];
        if (args.length > 1) {
            super.parseArguments(args[1]);
        } else {
            super.parseArguments(null);
        }
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        NusModule module = moduleManager.getModule(moduleName);
        if (module == null) {
            throw new InvalidArgumentException("Module not found! Type 'module view' for the list of modules.");
        }
        commandMap.setWorkspace(moduleName);
        return super.execute(ui, moduleManager);
    }
}
