package medbot.command;

import medbot.Parser;
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
            Parser.switchViewType();
        } else {
            Parser.setViewType(newViewType);
        }

        ui.clearConsoleFromIde();
        ui.printSwitchedViewMessage();
    }
}
