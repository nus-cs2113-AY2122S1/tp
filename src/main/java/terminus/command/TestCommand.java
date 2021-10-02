package terminus.command;

import terminus.module.NusModule;
import terminus.ui.Ui;

public class TestCommand extends Command {
    private String arguments;
    
    @Override
    public String getFormat() {
        return "test";
    }

    @Override
    public String getHelpMessage() {
        return null;
    }

    @Override
    public void parseArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) {
        ui.printSection(arguments);
        return new CommandResult(true,false);
    }
}
