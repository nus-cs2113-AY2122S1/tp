package medbot.command.personcommand.staffcommand;

import medbot.Scheduler;
import medbot.command.personcommand.ArchivePersonCommand;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;
import medbot.utilities.ViewType;

public class ArchiveStaffCommand extends ArchivePersonCommand {
    public ArchiveStaffCommand(int staffId) {
        super(staffId);
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.archiveStaff(personId);
        String archiveStaffMessage = Ui.getArchivePersonMessage(personId, ViewType.MEDICAL_STAFF_INFO);
        ui.printOutput(archiveStaffMessage);
    }
}
