package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ShowPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class ShowPatientCommand extends ShowPersonCommand {
    public ShowPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.showPatient(personId);
        String showPatientMessage = Ui.getShowPersonMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(showPatientMessage);
    }
}
