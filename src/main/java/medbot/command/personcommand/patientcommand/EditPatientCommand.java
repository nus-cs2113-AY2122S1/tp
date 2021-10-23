package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.EditPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.person.Patient;
import medbot.utilities.ViewType;

public class EditPatientCommand extends EditPersonCommand {

    public EditPatientCommand(int patientId, Patient patient) {
        super(patientId, patient);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.getPatientList().editPerson(personId, person);
        String patientInfo = scheduler.getPatientList().getPersonInfo(personId);
        String editPatientMessage = Ui.getEditMessage(personId, patientInfo, ViewType.PATIENT_INFO);
        ui.printOutput(editPatientMessage);
    }
}
