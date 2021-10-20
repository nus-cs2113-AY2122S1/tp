package medbot.command.patientcommand;

import medbot.command.EditPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;
import medbot.person.Patient;

public class EditPatientCommand extends EditPersonCommand {

    public EditPatientCommand(int patientId, Patient patient) {
        super(patientId, patient);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        personList.editPerson(personId, person);
        String patientInfo = personList.getPersonInfo(personId);
        String editPatientMessage = ui.getEditPatientMessage(personId, patientInfo);
        ui.printOutput(editPatientMessage);
    }
}
