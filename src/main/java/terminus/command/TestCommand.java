package terminus.command;

import terminus.module.NusModule;
import terminus.ui.Ui;

public class TestCommand extends Command {
    private String arguments;
    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public StringBuilder getHelpMessage() {
        return null;
    }

    @Override
    public void parseArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute(Ui ui, NusModule module) {
        System.out.println(arguments);
        return new CommandResult(true,false,null,null);
    }
}
