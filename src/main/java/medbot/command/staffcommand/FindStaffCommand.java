package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.FindPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

import java.util.List;

public class FindStaffCommand extends FindPersonCommand {
    public FindStaffCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        List<String> patients = personList.findPersons(parameters);
        String findPatientsMessage = ui.getFindPatientsMessage(patients);
        ui.printOutput(findPatientsMessage);
    }
}
