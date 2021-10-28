package medbot.command;

import medbot.parser.Parser;
import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

//@@author Kureans

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
