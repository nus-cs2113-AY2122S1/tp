package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.ui.SchedulerUi;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;

public class ListAppointmentCommand extends Command {

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output = scheduler.listAllAppointments();
        if (output.isEmpty()) {
            output = SchedulerUi.getNoAppointmentsMessage();
        } else {
            output = SchedulerUi.getListAppointmentListMessage(output);
        }
        ui.printOutput(output);
    }
}
