package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_EXIT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_EXIT;
    }

    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager) throws InvalidCommandException {
        TerminusLogger.info("Executing Exit Command");
        return new CommandResult(true, true);
    }
}
