package medbot.command.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.ListPersonCommand;

public class ListStaffCommand extends ListPersonCommand {

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        //todo change to getAllStaffString
        String allStaffString = ui.getAllPatientsString(scheduler.getMedicalStaffList());
        ui.printOutput(allStaffString);
    }
}
