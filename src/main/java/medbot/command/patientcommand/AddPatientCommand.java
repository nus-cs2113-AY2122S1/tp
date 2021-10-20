package medbot.command.patientcommand;

import medbot.command.AddPersonCommand;
import medbot.Ui;
import medbot.list.PersonList;
import medbot.person.Patient;

public class AddPatientCommand extends AddPersonCommand {
    public AddPatientCommand(Patient patient) {
        super(patient);
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        int patientId = personList.addPerson(person);
        String successMessage = ui.getAddPatientMessage(patientId);
        ui.printOutput(successMessage);
    }
}
