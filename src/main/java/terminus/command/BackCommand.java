package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
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
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        TerminusLogger.info("Executing Back Command");
        MainCommandParser mainParser = MainCommandParser.getInstance();
        TerminusLogger.info("Changing Workspace to:" + mainParser.getWorkspace());
        return new CommandResult(true, mainParser);
    }
}
