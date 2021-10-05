package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
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
    public void parseArguments(String arguments) {

    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        return new CommandResult(true, true);
    }
}
