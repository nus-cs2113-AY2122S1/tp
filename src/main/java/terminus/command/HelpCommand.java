package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public class HelpCommand extends Command {

    public static final String HELP_MENU_MESSAGE = "\nHelp Menu\n---------";
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
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        TerminusLogger.info("Executing Help Command");
        ui.printSection(HELP_MENU_MESSAGE);
        ui.printSection(commandMap.getHelpMenu());
        return new CommandResult(true);
    }
}
