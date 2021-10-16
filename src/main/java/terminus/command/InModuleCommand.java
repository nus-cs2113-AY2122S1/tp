package terminus.command;

import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.InModuleCommandParser;
import terminus.ui.Ui;

public abstract class InModuleCommand extends WorkspaceCommand {
    private InModuleCommandParser commandMap;

    public InModuleCommand(InModuleCommandParser commandMap) {
        super(commandMap);
        this.commandMap = commandMap;
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        commandMap.setModuleName(getModuleName());
        return super.execute(ui, moduleManager);
    }
}
