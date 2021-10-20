package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.DeletePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class DeleteStaffCommand extends DeletePersonCommand {
    public DeleteStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.deletePerson(personId);
        //Change to deleteStaff
        String deletePatientMessage = ui.getDeletePatientMessage(personId);
        ui.printOutput(deletePatientMessage);
    }
}
