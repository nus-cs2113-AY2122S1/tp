package medbot.command.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.DeletePersonCommand;
import medbot.exceptions.MedBotException;

public class DeleteStaffCommand extends DeletePersonCommand {
    public DeleteStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.getMedicalStaffList().deletePerson(personId);
        //todo change to getDeleteStaffMessage
        String deleteStaffMessage = ui.getDeletePatientMessage(personId);
        ui.printOutput(deleteStaffMessage);
    }
}
