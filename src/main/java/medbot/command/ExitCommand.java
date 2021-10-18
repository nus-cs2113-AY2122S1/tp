package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) {
        String exitMessage = ui.getExitMessage();
        ui.printOutput(exitMessage);
    }
}
