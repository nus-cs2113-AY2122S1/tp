package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.ViewPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

//@@author jushg
public class ViewStaffCommand extends ViewPersonCommand {
    public ViewStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String staffInfo = scheduler.getStaffInfo(personId);
        String viewStaffMessage = Ui.getViewMessage(staffInfo, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(viewStaffMessage);
    }
}
