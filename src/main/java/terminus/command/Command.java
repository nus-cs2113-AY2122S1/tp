package terminus.command;

import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class Command {

    public Command() {

    }

    public String getFormat() {
        return null;
    }

    public String getHelpMessage() {
        return null;
    }

    public void parseArguments(String arguments) throws InvalidArgumentException, InvalidTimeFormatException {

    }

    public CommandResult execute(Ui ui, NusModule module)
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        return null;
    }
}
