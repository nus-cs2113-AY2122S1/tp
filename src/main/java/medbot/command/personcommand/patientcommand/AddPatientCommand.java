package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.AddPersonCommand;
import medbot.ui.Ui;
import medbot.exceptions.MedBotException;
import medbot.person.Patient;
import medbot.utilities.ViewType;

public class AddPatientCommand extends AddPersonCommand {
    public AddPatientCommand(Patient patient) {
        super(patient);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        int patientId = scheduler.addPatient(person);
        String addPatientMessage = Ui.getAddMessage(patientId, ViewType.PATIENT_INFO);
        ui.printOutput(addPatientMessage);
    }
}
