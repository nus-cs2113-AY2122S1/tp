package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;

public class SwitchCommand extends Command {

    @Override
    public void execute(PatientList patientList, Ui ui) {
        Parser.switchViewType();
        ui.clearConsoleFromIde();
        ui.printSwitchedViewMessage();
    }
}
