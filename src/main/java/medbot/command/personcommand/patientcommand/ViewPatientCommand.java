package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ViewPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class ViewPatientCommand extends ViewPersonCommand {

    public ViewPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String patientInfo = scheduler.getPatientInfo(personId);
        String viewPatientMessage = Ui.getViewMessage(patientInfo, ViewType.PATIENT_INFO);
        ui.printOutput(viewPatientMessage);
    }
}
