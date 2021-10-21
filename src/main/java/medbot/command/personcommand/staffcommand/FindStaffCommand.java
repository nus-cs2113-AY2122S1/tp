package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.personcommand.FindPersonCommand;
import medbot.exceptions.MedBotException;

import java.util.List;

public class FindStaffCommand extends FindPersonCommand {
    public FindStaffCommand(String[] parameters) {
        super(parameters);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        List<String> patients = scheduler.getMedicalStaffList().findPersons(parameters);
        //todo change to getFindStaffMessage()
        String findStaffMessage = ui.getFindPatientsMessage(patients);
        ui.printOutput(findStaffMessage);
    }
}
