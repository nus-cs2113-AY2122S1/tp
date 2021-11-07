package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.FindPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

import java.util.List;

//@@author jushg
public class FindStaffCommand extends FindPersonCommand {
    public FindStaffCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        List<String> staffs = scheduler.findStaff(parameters);
        String findStaffMessage = Ui.getFindPersonsMessage(staffs, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(findStaffMessage);
    }
}
