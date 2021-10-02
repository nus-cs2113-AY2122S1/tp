package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class WorkspaceCommand extends Command {

    protected String arguments;
    protected CommandParser commandMap;

    public WorkspaceCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void parseArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        if (arguments != null && !arguments.isBlank()) {
            return commandMap.parseCommand(arguments).execute(ui, module);
        } else {
            return new CommandResult(true, commandMap);
        }
    }
}
