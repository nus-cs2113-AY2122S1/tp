package medbot.command.appointmentcommand;

import medbot.Appointment;
import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

public class EditAppointmentCommand extends Command {
    int appointmentId = 0;
    Appointment appointment = null;

    public EditAppointmentCommand(int appointmentId, Appointment appointment) {
        this.appointmentId = appointmentId;
        this.appointment = appointment;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.editAppointment(appointmentId, appointment);
        Appointment editedAppointment = scheduler.getAppointment(appointmentId);
        String editAppointmentMessage = Ui.getEditMessage(editedAppointment.getId(), editedAppointment.toString(),
                ViewType.SCHEDULER);
        ui.printOutput(editAppointmentMessage);
    }
}
