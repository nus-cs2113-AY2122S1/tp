package medbot.command;

import medbot.MedBotException;
import medbot.PatientList;
import medbot.Ui;

public class DeletePatientCommand extends Command {
    private int patientId = 0;
    
    public DeletePatientCommand(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) throws MedBotException {
        patientList.deletePatient(patientId);
        ui.printOutput("Patient with id " + patientId + " deleted from system.");
    }
}
