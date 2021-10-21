package medbot.command.appointmentcommand;

import medbot.Appointment;
import medbot.Scheduler;
import medbot.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;

public class AddAppointmentCommand extends Command {
    Appointment appointment = null;

    public AddAppointmentCommand(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.addAppointment(appointment);
        ui.printOutput("Appointment added: " + appointment);
    }

}
