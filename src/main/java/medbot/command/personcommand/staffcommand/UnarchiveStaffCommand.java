package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.command.personcommand.UnarchivePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class UnarchiveStaffCommand extends UnarchivePersonCommand {
    public UnarchiveStaffCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.unarchiveStaff(personId);
        String unarchiveStaffMessage = Ui.getUnarchivePersonMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(unarchiveStaffMessage);
    }
}
