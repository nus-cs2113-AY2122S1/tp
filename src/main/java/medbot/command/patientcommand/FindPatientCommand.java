package medbot.command.patientcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.FindPersonCommand;
import medbot.exceptions.MedBotException;

import java.util.List;

public class FindPatientCommand extends FindPersonCommand {
    public FindPatientCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        List<String> patients = scheduler.getPatientList().findPersons(parameters);
        String findPatientsMessage = ui.getFindPatientsMessage(patients);
        ui.printOutput(findPatientsMessage);
    }
}