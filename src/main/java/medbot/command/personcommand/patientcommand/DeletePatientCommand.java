package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.DeletePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class DeletePatientCommand extends DeletePersonCommand {

    public DeletePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.deletePatient(personId);
        String deletePatientMessage = Ui.getDeleteMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(deletePatientMessage);
    }
}
