package medbot.command;

import medbot.exceptions.MedBotException;
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
        String deletePatientMessage = ui.getDeletePatientMessage(patientId);
        ui.printOutput(deletePatientMessage);
    }
}
