package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ViewPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.PatientUi;
import medbot.ui.Ui;

public class ViewPatientCommand extends ViewPersonCommand {

    public ViewPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String patientInfo = scheduler.getPatientList().getPersonInfo(personId);
        String viewPatientMessage = PatientUi.getPatientInfo(patientInfo);
        ui.printOutput(viewPatientMessage);
    }
}
