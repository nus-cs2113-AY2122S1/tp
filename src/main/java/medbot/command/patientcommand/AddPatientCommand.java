package medbot.command.patientcommand;

import medbot.Scheduler;
import medbot.command.AddPersonCommand;
import medbot.Ui;
import medbot.exceptions.MedBotException;
import medbot.person.Patient;

public class AddPatientCommand extends AddPersonCommand {
    public AddPatientCommand(Patient patient) {
        super(patient);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        int patientId = scheduler.getPatientList().addPerson(person);
        String addPatientMessage = ui.getAddPatientMessage(patientId);
        ui.printOutput(addPatientMessage);
    }
}
