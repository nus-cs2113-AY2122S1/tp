package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.person.PersonType;

public class ViewAppointmentCommand extends Command {
    protected int personId = 0;
    protected PersonType personType;

    public ViewAppointmentCommand(int personId, PersonType personType) {
        this.personId = personId;
        this.personType = personType;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output = "";
        switch (personType) {
        case PATIENT:
            output = scheduler.listPatientAppointments(personId);
            break;
        case STAFF:
            output = scheduler.listMedicalStaffAppointments(personId);
            break;
        default:
        }

        ui.printOutput(output);
    }
}
