package terminus.command;

import terminus.module.NusModule;
import terminus.ui.Ui;

@Deprecated
public class TestCommand extends Command {
    
    @Override
    public String getFormat() {
        return "test";
    }

    @Override
    public String getHelpMessage() {
        return "This is testing command.";
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) {
        ui.printSection(arguments);
        return new CommandResult(true,false);
    }
}
