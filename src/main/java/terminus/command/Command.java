package terminus.command;

import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class Command {

    public Command() {

    }

    public abstract String getFormat();

    public abstract String getHelpMessage();

    public abstract void parseArguments(String arguments) throws InvalidArgumentException;

    public abstract CommandResult execute(Ui ui, NusModule module)
            throws InvalidCommandException, InvalidArgumentException;

}
