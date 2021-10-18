package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;
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
    public void execute(PatientList patientList, Ui ui) {
        if (newViewType == null) {
            Parser.switchViewType();
        } else {
            Parser.setViewType(newViewType);
        }

        ui.clearConsoleFromIde();
        ui.printSwitchedViewMessage();
    }
}
