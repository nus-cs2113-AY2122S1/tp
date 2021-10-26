package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.person.PersonType;
import medbot.ui.SchedulerUi;
import medbot.ui.Ui;
import medbot.utilities.FilterType;

public class FindAppointmentCommand extends Command {
    protected int personId = 0;
    protected PersonType personType;
    protected FilterType filterType;
    protected int dateTimeCode;

    public FindAppointmentCommand(int personId, PersonType personType, FilterType filterType, int dateTimeCode) {
        this.personId = personId;
        this.personType = personType;
        this.filterType = filterType;
        this.dateTimeCode = dateTimeCode;

    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output = "";
        switch (personType) {
        case PATIENT:
            output = scheduler.listPatientAppointments(personId, filterType, dateTimeCode);
            break;
        case STAFF:
            output = scheduler.listMedicalStaffAppointments(personId, filterType, dateTimeCode);
            break;
        default:
        }
        if (output.isEmpty()) {
            output = SchedulerUi.getNoAppointmentsFoundMessage();
        } else {
            output = SchedulerUi.getFindAppointmentListMessage(output);
        }
        ui.printOutput(output);
    }

}
