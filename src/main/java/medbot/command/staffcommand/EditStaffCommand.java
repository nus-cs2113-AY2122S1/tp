package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.EditCommand;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;
import medbot.person.Staff;

public class EditStaffCommand extends EditCommand {
    public EditStaffCommand(int staffId, Staff staff) {
        super(staffId, staff);
    }
    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.editPerson(personId, person);
        String patientInfo = personList.getPersonInfo(personId);
        String editPatientMessage = ui.getEditPatientMessage(personId, patientInfo);
        ui.printOutput(editPatientMessage);
    }
}
