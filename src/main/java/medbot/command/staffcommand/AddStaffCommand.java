package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.AddPersonCommand;
import medbot.list.PersonList;
import medbot.person.Staff;

public class AddStaffCommand extends AddPersonCommand {
    public AddStaffCommand(Staff staff) {
        super(staff);
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        int patientId = personList.addPerson(person);
        //Need to change
        String successMessage = ui.getAddPatientMessage(patientId);
        ui.printOutput(successMessage);
    }
}
