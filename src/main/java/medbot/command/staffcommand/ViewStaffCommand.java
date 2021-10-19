package medbot.command.staffcommand;

import medbot.Ui;
import medbot.command.ViewCommand;
import medbot.exceptions.MedBotException;
import medbot.list.PersonList;

public class ViewStaffCommand extends ViewCommand {
    public ViewStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(PersonList personList, Ui ui) throws MedBotException {
        String patientInfo = personList.getPersonInfo(personId);
        ui.printOutput(ui.getPatientInfo(patientInfo));
    }
}
