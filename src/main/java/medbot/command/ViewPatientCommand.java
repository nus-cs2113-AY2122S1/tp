package medbot.command;

import medbot.MedBotException;
import medbot.PatientList;
import medbot.Ui;

public class ViewPatientCommand extends Command {
    private int patientId = 0;

    public ViewPatientCommand(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) throws MedBotException {
        String patientInfo = patientList.getPatientInfo(patientId);
        ui.printOutput("Here's the patient with id " + patientId + ": " + patientInfo);
    }
}
