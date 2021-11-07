package medbot.command.appointmentcommand;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.command.Command;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

public class ViewAppointmentCommand extends Command {
    protected int appointmentId;

    public ViewAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;

    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String appointmentInfo = scheduler.getAppointment(appointmentId).toString();
        String viewAppointmentMessage = Ui.getViewMessage(appointmentInfo, ViewType.SCHEDULER);
        ui.printOutput(viewAppointmentMessage);
    }
}
