package medbot.command;

import medbot.parser.Parser;
import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

//@@author Kureans

public class SwitchCommand extends Command {
    private ViewType newViewType = null;
    private boolean isChanged = false;

    public SwitchCommand(ViewType viewType, boolean isChanged) {
        newViewType = viewType;
        this.isChanged = isChanged;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        if (isChanged) {
            Parser.setViewType(newViewType);
            Ui.clearConsoleFromIde();
            ui.printSwitchedViewMessage(newViewType);
        } else {
            ui.printUnchangedViewMessage(newViewType);
        }
    }
}
