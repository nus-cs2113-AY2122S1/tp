package medbot.command.patientcommand;

import medbot.Scheduler;
import medbot.command.ViewPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;

public class ViewPatientCommand extends ViewPersonCommand {

    public ViewPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String patientInfo = scheduler.getPatientList().getPersonInfo(personId);
        String viewPatientMessage = ui.getPatientInfo(patientInfo);
        ui.printOutput(viewPatientMessage);
    }
}
