package medbot.command.personcommand.patientcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ArchivePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class ArchivePatientCommand extends ArchivePersonCommand {
    public ArchivePatientCommand(int patientId) {
        super(patientId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.archivePatient(personId);
        String archivePatientMessage = Ui.getArchivePersonMessage(personId, ViewType.PATIENT_INFO);
        ui.printOutput(archivePatientMessage);
    }
}
