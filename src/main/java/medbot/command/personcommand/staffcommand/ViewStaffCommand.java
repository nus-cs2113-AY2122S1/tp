package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.personcommand.ViewPersonCommand;
import medbot.exceptions.MedBotException;

public class ViewStaffCommand extends ViewPersonCommand {
    public ViewStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String staffInfo = scheduler.getMedicalStaffList().getPersonInfo(personId);
        //todo change ot getStaffInfo()
        String viewStaffMessage = ui.getPatientInfo(staffInfo);
        ui.printOutput(viewStaffMessage);
    }
}
