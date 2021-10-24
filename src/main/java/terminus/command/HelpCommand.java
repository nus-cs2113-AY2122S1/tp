package terminus.command;

import java.util.Arrays;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.CommandParser;

public class HelpCommand extends Command {

    private final CommandParser commandMap;

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
    public CommandResult execute(ModuleManager moduleManager) throws InvalidCommandException {
        TerminusLogger.info("Executing Help Command");
        StringBuilder stringBuilder = new StringBuilder(Messages.HELP_MENU_MESSAGE);
        String helpMessage = Arrays.stream(commandMap.getHelpMenu())
            .reduce((x, y) -> x + "\n" + y)
            .orElse("There are no commands in this workspace.");
        stringBuilder.append(helpMessage);
        return new CommandResult(stringBuilder.toString());
    }
}
