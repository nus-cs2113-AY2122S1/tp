package medbot.command.appointmentcommand;

import medbot.Appointment;
import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;

public class ViewAppointmentCommand extends Command {
    protected int appointmentId;

    public ViewAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;

    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output = "";
        Appointment appointment = scheduler.getAppointment(appointmentId);
        output += appointment;
        ui.printOutput(output);
    }
}
