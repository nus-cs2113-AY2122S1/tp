package medbot.command;

import medbot.CommandManager;
import medbot.Ui;
import medbot.list.PersonList;
import medbot.utilities.ViewType;

public class SwitchCommand extends Command {
    private ViewType newViewType = null;

    public SwitchCommand() {
        newViewType = null;
    }

    public SwitchCommand(ViewType viewType) {
        newViewType = viewType;
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        if (newViewType == null) {
            CommandManager.switchViewType();
        } else {
            CommandManager.setViewType(newViewType);
        }

        ui.clearConsoleFromIde();
        ui.printSwitchedViewMessage();
    }
}
