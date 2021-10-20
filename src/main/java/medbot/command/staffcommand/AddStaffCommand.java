package medbot.command.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.AddPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.person.Staff;

public class AddStaffCommand extends AddPersonCommand {
    public AddStaffCommand(Staff staff) {
        super(staff);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        int staffId = scheduler.getMedicalStaffList().addPerson(person);
        //todo change to getAddStaffMessage
        String addStaffMessage = ui.getAddPatientMessage(staffId);
        ui.printOutput(addStaffMessage);
    }
}
