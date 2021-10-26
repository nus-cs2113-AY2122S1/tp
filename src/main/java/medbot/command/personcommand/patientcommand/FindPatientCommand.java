package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.personcommand.FindPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

import java.util.List;

public class FindPatientCommand extends FindPersonCommand {
    public FindPatientCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        List<String> patients = scheduler.findPatients(parameters);
        String findPatientsMessage = Ui.getFindPersonsMessage(patients, ViewType.PATIENT_INFO);
        ui.printOutput(findPatientsMessage);
    }
}