package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.UnarchivePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class UnarchivePatientCommand extends UnarchivePersonCommand {
    public UnarchivePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.unarchivePatient(personId);
        String unarchivePatientMessage = Ui.getUnarchivePersonMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(unarchivePatientMessage);
    }
}
