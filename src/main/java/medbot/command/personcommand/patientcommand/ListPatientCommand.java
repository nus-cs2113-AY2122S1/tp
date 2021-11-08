package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.ui.PatientUi;
import medbot.ui.Ui;
import medbot.command.personcommand.ListPersonCommand;


public class ListPatientCommand extends ListPersonCommand {
    public ListPatientCommand(boolean getHiddenPersons) {
        super(getHiddenPersons);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        boolean isHidden = getHidden();
        String allPatientsString = PatientUi.getAllPatientsString(scheduler.listPatients(isHidden), isHidden);
        ui.printOutput(allPatientsString);
    }
}
