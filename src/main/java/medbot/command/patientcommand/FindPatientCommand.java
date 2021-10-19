package medbot.command.patientcommand;

import medbot.Ui;
import medbot.command.FindCommand;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

import java.util.List;

public class FindPatientCommand extends FindCommand {
    public FindPatientCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        List<String> patients = personList.findPersons(parameters);
        String findPatientsMessage = ui.getFindPatientsMessage(patients);
        ui.printOutput(findPatientsMessage);
    }
}
