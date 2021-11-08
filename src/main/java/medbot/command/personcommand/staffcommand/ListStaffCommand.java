package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.StaffUi;
import medbot.ui.Ui;
import medbot.command.personcommand.ListPersonCommand;

//@@author jushg
public class ListStaffCommand extends ListPersonCommand {
    public ListStaffCommand(boolean getHidden) {
        super(getHidden);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        boolean isHidden = getHidden();
        String allStaffString = StaffUi.getAllStaffsString(scheduler.listStaff(isHidden), isHidden);
        ui.printOutput(allStaffString);
    }
}
