package terminus.parser;

import terminus.command.Command;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public abstract class InModuleCommandParser extends CommandParser {

    private String moduleName;

    /**
     * Initializes the commandMap. Adds some default commands to it.
     *
     * @param workspace The name of the workspace.
     */
    public InModuleCommandParser(String workspace) {
        super(workspace);
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public Command parseCommand(String command) throws InvalidCommandException, InvalidArgumentException {
        Command cmd = super.parseCommand(command);
        cmd.setModuleName(moduleName);
        return cmd;
    }

    @Override
    public String getWorkspace() {
        return moduleName + " > " + super.getWorkspace();
    }
}
