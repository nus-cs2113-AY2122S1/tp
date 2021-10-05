package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class BackCommand extends Command {

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_BACK;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_BACK;
    }

    @Override
    public void parseArguments(String arguments) {
        return;
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        MainCommandParser mainParser = MainCommandParser.getInstance();
        return new CommandResult(true, mainParser);
    }
}
