package medbot.command.patientCommand;

import medbot.command.DeleteCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;

public class DeletePatientCommand extends DeleteCommand {

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
