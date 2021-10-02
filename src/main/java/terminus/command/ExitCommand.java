package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public String getFormat() {
        return "exit";
    }

    @Override
    public StringBuilder getHelpMessage() {
        return new StringBuilder("Exits the program.");
    }

    @Override
    public void parseArguments(String arguments) {

    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException {
        return new CommandResult(true, true, null, null);
    }
}
