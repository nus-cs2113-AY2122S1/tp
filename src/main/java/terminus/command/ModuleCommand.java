package terminus.command;

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
        return "module";
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return "Move to the module workspace";
    }
}
