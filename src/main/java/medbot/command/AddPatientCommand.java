package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;
import medbot.person.Patient;

public class AddPatientCommand extends Command {
    private Patient patient;

    public AddPatientCommand(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) {
        int patientId = patientList.addPatient(patient);
        String successMessage = ui.getAddPatientMessage(patientId);
        ui.printOutput(successMessage);
    }
}
