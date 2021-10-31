package terminus.parser;

import terminus.command.Command;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public abstract class InnerModuleCommandParser extends CommandParser {

    private String moduleName;

    /**
     * Initializes the commandMap. Adds some default commands to it.
     *
     * @param workspace The name of the workspace.
     */
    public InnerModuleCommandParser(String workspace) {
        super(workspace);
    }

    /**
     * Returns the module name for the current workspace.
     *
     * @return The string containing the module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the module name for the current workspace.
     *
     * @param moduleName The module name to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public Command parseCommand(String command) throws InvalidCommandException, InvalidArgumentException {
        Command cmd = super.parseCommand(command);
        cmd.setModuleName(moduleName.toUpperCase());
        return cmd;
    }

    /**
     * Returns a workspace that contains the moduleName and the current work space.
     *
     * @return The consolidated workspace name
     */
    @Override
    public String getWorkspace() {
        return getWorkspace(false);
    }

    public String getWorkspace(boolean isOriginal) {
        if (isOriginal) {
            return super.getWorkspace();
        }
        return moduleName + " > " + super.getWorkspace();
    }
}
