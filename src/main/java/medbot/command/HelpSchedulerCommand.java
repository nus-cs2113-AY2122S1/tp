package medbot.command;

import medbot.Scheduler;
import medbot.Ui;

public class HelpSchedulerCommand extends Command {

    private CommandType commandType = null;

    public HelpSchedulerCommand() {
    }

    public HelpSchedulerCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public void execute(Scheduler scheduler, Ui ui) {
        String output;
        if (commandType == null) {
            output = ui.getCommandList();
            ui.printOutput(output);
            return;
        }
        switch (commandType) {
        case ADD_APPOINTMENT:
            output = ui.getAddAppointmentHelpMessage();
            break;
        case DELETE_APPOINTMENT:
            output = ui.getDeleteAppointmentHelpMessage();
            break;
        case EDIT_APPOINTMENT:
            output = ui.getEditAppointmentHelpMessage();
            break;
        case LIST_APPOINTMENT:
            output = ui.getListAppointmentHelpMessage();
            break;
        case VIEW_APPOINTMENT:
            output = ui.getViewAppointmentHelpMessage();
            break;
        case SWITCH:
            output = ui.getSwitchHelpMessage();
            break;
        case EXIT:
            output = ui.getExitHelpMessage();
            break;
        default:
            output = ui.getCommandList();
            break;
        }
        ui.printOutput(output);
    }
}
