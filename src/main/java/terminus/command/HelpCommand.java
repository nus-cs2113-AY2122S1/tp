package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public class HelpCommand extends Command {

    private CommandParser commandMap;

    public HelpCommand(CommandParser commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_HELP;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_HELP;
    }

    @Override
    public void parseArguments(String arguments) {

    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        ui.printSection(commandMap.getHelpMenu());
        return new CommandResult(true);
    }
}
