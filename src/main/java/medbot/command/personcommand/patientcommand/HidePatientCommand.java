package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.HidePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class HidePatientCommand extends HidePersonCommand {
    public HidePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.hidePatient(personId);
        String hidePatientMessage = Ui.getHidePersonMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(hidePatientMessage);
    }
}
