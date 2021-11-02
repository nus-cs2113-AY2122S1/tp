package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.AddPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.person.Staff;
import medbot.utilities.ViewType;

public class AddStaffCommand extends AddPersonCommand {
    public AddStaffCommand(Staff staff) {
        super(staff);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.addStaff(person);
        String addStaffMessage = Ui.getAddMessage(ViewType.MEDICAL_STAFF_INFO, person.toString());
        ui.printOutput(addStaffMessage);
    }
}
