package medbot.command.appointmentcommand;

import medbot.Appointment;
import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

public class AddAppointmentCommand extends Command {
    Appointment appointment = null;

    public AddAppointmentCommand(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.addAppointment(appointment);
        String addAppointmentMessage = Ui.getAddMessage(ViewType.SCHEDULER, appointment.toString());
        ui.printOutput(addAppointmentMessage);
    }

}
