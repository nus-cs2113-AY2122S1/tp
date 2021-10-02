package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class BackCommand extends Command {

    @Override
    public String getFormat() {
        return "back";
    }

    @Override
    public String getHelpMessage() {
        return "Returns to the parent workspace.";
    }

    @Override
    public void parseArguments(String arguments) {

    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        MainCommandParser mainParser = MainCommandParser.getInstance();
        return new CommandResult(true, mainParser);
    }
}
