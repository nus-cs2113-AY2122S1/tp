package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.command.personcommand.HidePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class HideStaffCommand extends HidePersonCommand {
    public HideStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.hideStaff(personId);
        String hideStaffMessage = Ui.getHidePersonMessage(personId, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(hideStaffMessage);
    }
}
