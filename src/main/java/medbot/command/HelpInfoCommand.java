package medbot.command;

import medbot.Scheduler;
import medbot.Ui;

public class HelpInfoCommand extends Command {

    private CommandType commandType = null;

    public HelpInfoCommand() {
    }

    public HelpInfoCommand(CommandType commandType) {
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
        case ADD_PERSON:
            output = ui.getAddInfoHelpMessage();
            break;
        case DELETE_PERSON:
            output = ui.getDeleteInfoHelpMessage();
            break;
        case EDIT_PERSON:
            output = ui.getEditInfoHelpMessage();
            break;
        case LIST_PERSON:
            output = ui.getListInfoHelpMessage();
            break;
        case FIND_PERSON:
            output = ui.getFindInfoHelpMessage();
            break;
        case VIEW_PERSON:
            output = ui.getViewInfoHelpMessage();
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
