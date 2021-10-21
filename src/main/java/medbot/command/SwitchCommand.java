package medbot.command;

import medbot.Parser;
import medbot.Scheduler;
import medbot.Ui;
import medbot.utilities.ViewType;

public class SwitchCommand extends Command {
    private ViewType newViewType = null;

    public SwitchCommand(ViewType viewType) {
        newViewType = viewType;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        Parser.setViewType(newViewType);

        ui.clearConsoleFromIde();
        ui.printSwitchedViewMessage(newViewType);
    }
}
