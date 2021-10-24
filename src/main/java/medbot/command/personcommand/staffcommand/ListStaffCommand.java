package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.StaffUi;
import medbot.ui.Ui;
import medbot.command.personcommand.ListPersonCommand;

public class ListStaffCommand extends ListPersonCommand {

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String allStaffString = StaffUi.getAllStaffsString(scheduler.listStaff());
        ui.printOutput(allStaffString);
    }
}
