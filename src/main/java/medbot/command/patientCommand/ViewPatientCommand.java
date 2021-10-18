package medbot.command.patientCommand;

import medbot.command.ViewCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;

public class ViewPatientCommand extends ViewCommand {

    public ViewPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        String patientInfo = personList.getPersonInfo(personId);
        ui.printOutput(ui.getPatientInfo(patientInfo));
    }
}
