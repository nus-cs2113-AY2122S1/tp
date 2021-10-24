package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.ui.PatientUi;
import medbot.ui.Ui;
import medbot.command.personcommand.ListPersonCommand;


public class ListPatientCommand extends ListPersonCommand {

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String allPatientsString = PatientUi.getAllPatientsString(scheduler.listPatients());
        ui.printOutput(allPatientsString);
    }
}
