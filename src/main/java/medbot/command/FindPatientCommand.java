package medbot.command;

import medbot.PatientList;
import medbot.Ui;
import medbot.exceptions.MedBotException;

import java.util.List;

public class FindPatientCommand extends Command {
    String[] parameters;

    public FindPatientCommand(String[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) throws MedBotException {
        List<String> patients = patientList.findPatients(parameters);
        String findPatientsMessage = ui.getFindPatientsMessage(patients);
        ui.printOutput(findPatientsMessage);
    }
}
