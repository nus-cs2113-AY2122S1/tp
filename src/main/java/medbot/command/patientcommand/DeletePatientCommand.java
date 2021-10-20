package medbot.command.patientcommand;

import medbot.command.DeletePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;

public class DeletePatientCommand extends DeletePersonCommand {

    public DeletePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.deletePerson(personId);
        String deletePatientMessage = ui.getDeletePatientMessage(personId);
        ui.printOutput(deletePatientMessage);
    }
}
