package terminus.command;

import java.io.IOException;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.parser.ModuleWorkspaceCommandParser;

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
        return CommonFormat.COMMAND_GO_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_GO;
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
    public CommandResult execute(ModuleManager moduleManager)
        throws InvalidCommandException, InvalidArgumentException, IOException {
        NusModule module = moduleManager.getModule(moduleName);
        if (module == null) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MODULE_NOT_FOUND);
        }
        commandMap.setWorkspace(moduleName);
        return super.execute(moduleManager);
    }
}
