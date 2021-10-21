package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.personcommand.EditPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.person.Staff;

public class EditStaffCommand extends EditPersonCommand {
    public EditStaffCommand(int staffId, Staff staff) {
        super(staffId, staff);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.getMedicalStaffList().editPerson(personId, person);
        String staffInfo = scheduler.getMedicalStaffList().getPersonInfo(personId);
        //todo change to getEditStaffMessage()
        String editStaffMessage = ui.getEditPatientMessage(personId, staffInfo);
        ui.printOutput(editStaffMessage);
    }
}
