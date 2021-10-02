package terminus.command;

import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class Command {

    public Command() {

    }

    public abstract String getFormat();
    public abstract StringBuilder getHelpMessage();
    public abstract void parseArguments(String arguments);
    public abstract CommandResult execute(Ui ui, NusModule module);
}
