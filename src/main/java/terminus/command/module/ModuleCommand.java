package terminus.command.module;

import terminus.command.WorkspaceCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.ModuleCommandParser;

public class ModuleCommand extends WorkspaceCommand {

    public ModuleCommand() {
        super(ModuleCommandParser.getInstance());
    }

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_MODULE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_MODULE;
    }
}
