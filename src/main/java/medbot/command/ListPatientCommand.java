package medbot.command;

import medbot.PatientList;
import medbot.Ui;

public class ListPatientCommand extends Command {

    @Override
    public void execute(PatientList patientList, Ui ui) {
        ui.printOutput("Here is a list of all patients:");
        patientList.listPatients();
    }
}
