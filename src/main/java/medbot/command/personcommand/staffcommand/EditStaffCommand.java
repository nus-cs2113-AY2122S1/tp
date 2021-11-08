package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.EditPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.person.Staff;
import medbot.utilities.ViewType;

//@@author jushg
public class EditStaffCommand extends EditPersonCommand {
    public EditStaffCommand(int staffId, Staff staff) {
        super(staffId, staff);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.editStaff(personId, person);
        String staffInfo = scheduler.getStaffInfo(personId);
        String editStaffMessage = Ui.getEditMessage(personId, staffInfo, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(editStaffMessage);
    }
}
