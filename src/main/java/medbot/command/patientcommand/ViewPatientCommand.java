package medbot.command.patientcommand;

import medbot.command.ViewPersonCommand;
import medbot.exceptions.MedBotException;
import medbot.Ui;
import medbot.list.PersonList;

public class ViewPatientCommand extends ViewPersonCommand {

    public ViewPatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        String patientInfo = personList.getPersonInfo(personId);
        ui.printOutput(ui.getPatientInfo(patientInfo));
    }
}
