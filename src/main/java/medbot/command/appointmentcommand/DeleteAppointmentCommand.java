package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;

public class DeleteAppointmentCommand extends Command {
    int appointmentId = 0;

    public DeleteAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        scheduler.deleteAppointment(appointmentId);
        ui.printOutput("deleted appointment with Id: " + appointmentId);
    }
}
