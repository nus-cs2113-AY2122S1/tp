package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;

public class ListAppointmentCommand extends Command {

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output = scheduler.getSchedulerAppointmentList().listAppointments();
        ui.printOutput(output);
    }
}
