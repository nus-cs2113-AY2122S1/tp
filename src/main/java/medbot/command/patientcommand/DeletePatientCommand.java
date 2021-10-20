package medbot.command.patientcommand;

import medbot.Scheduler;
import medbot.command.DeletePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;

public class DeletePatientCommand extends DeletePersonCommand {

    public DeletePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.getPatientList().deletePerson(personId);
        String deletePatientMessage = ui.getDeletePatientMessage(personId);
        ui.printOutput(deletePatientMessage);
    }
}
