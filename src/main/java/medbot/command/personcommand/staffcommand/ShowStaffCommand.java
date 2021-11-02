package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ShowPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class ShowStaffCommand extends ShowPersonCommand {
    public ShowStaffCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.showStaff(personId);
        String showStaffMessage = Ui.getShowPersonMessage(personId, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(showStaffMessage);
    }
}
