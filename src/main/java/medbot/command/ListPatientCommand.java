package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;

public class ListPatientCommand extends Command {

    @Override
    public void execute(PatientList patientList, Ui ui) {
        String allPatientsString = ui.getAllPatientsString(patientList);
        ui.printOutput(allPatientsString);
    }
}
