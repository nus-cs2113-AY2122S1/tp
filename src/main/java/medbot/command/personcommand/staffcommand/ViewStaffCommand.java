package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.StaffUi;
import medbot.ui.Ui;
import medbot.command.personcommand.ViewPersonCommand;
import medbot.exceptions.MedBotException;

public class ViewStaffCommand extends ViewPersonCommand {
    public ViewStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String staffInfo = scheduler.getMedicalStaffList().getPersonInfo(personId);
        String viewStaffMessage = StaffUi.getStaffInfo(staffInfo);
        ui.printOutput(viewStaffMessage);
    }
}
