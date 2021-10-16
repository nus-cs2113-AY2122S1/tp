package medbot.command;

import medbot.MedBotException;
import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;
import medbot.person.Patient;

public class EditPatientCommand extends Command {
    private int patientId = 0;
    private Patient patient = null;

    public EditPatientCommand(int patientId, Patient patient) {
        this.patientId = patientId;
        this.patient = patient;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) throws MedBotException {
        patientList.editPatient(patientId, patient);
        String patientInfo = patientList.getPatientInfo(patientId);
        String editPatientMessage = ui.getEditPatientMessage(patientId, patientInfo);
        ui.printOutput(editPatientMessage);
    }
}
