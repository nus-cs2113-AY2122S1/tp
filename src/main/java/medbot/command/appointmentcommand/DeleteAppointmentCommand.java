package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.ui.SchedulerUi;
import medbot.ui.Ui;
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
        String deleteAppointmentMessage = SchedulerUi.getDeleteAppointmentMessage(appointmentId);
        ui.printOutput(deleteAppointmentMessage);
    }
}
