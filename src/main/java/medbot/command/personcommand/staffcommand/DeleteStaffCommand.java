package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.DeletePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

//@@author jushg
public class DeleteStaffCommand extends DeletePersonCommand {
    public DeleteStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.deleteStaff(personId);
        String deleteStaffMessage = Ui.getDeleteMessage(personId, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(deleteStaffMessage);
    }
}
