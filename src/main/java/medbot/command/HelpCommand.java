package medbot.command;

import medbot.Scheduler;
import medbot.ui.Ui;
import medbot.exceptions.MedBotException;
import medbot.utilities.ViewType;

//@@author Kureans
public class HelpCommand extends Command {

    private CommandType commandType = null;
    private ViewType viewType = null;

    public HelpCommand(ViewType viewType) {
        this.viewType = viewType;
    }

    public HelpCommand(ViewType viewType, CommandType commandType) {
        this.viewType = viewType;
        this.commandType = commandType;
    }


    @Override
    public void execute(Scheduler scheduler, Ui ui) throws MedBotException {
        String output;
        if (commandType == null) {
            output = Ui.getCommandList(viewType);
            ui.printOutput(output);
            return;
        }
        switch (commandType) {
        case ADD:
            output = ui.getAddHelpMessage(viewType);
            break;
        case DELETE:
            output = ui.getDeleteHelpMessage(viewType);
            break;
        case EDIT:
            output = ui.getEditHelpMessage(viewType);
            break;
        case LIST:
            output = ui.getListHelpMessage(viewType);
            break;
        case FIND:
            output = ui.getFindHelpMessage(viewType);
            break;
        case VIEW:
            output = ui.getViewHelpMessage(viewType);
            break;
        case SWITCH:
            output = ui.getSwitchHelpMessage();
            break;
        case ARCHIVE:
            output = ui.getArchiveHelpMessage(viewType);
            break;
        case UNARCHIVE:
            output = ui.getUnarchiveHelpMessage(viewType);
            break;
        case EXIT:
            output = ui.getExitHelpMessage();
            break;
        case GET_VIEW:
            output = ui.getGetCurrentViewHelpMessage();
            break;
        default:
            output = Ui.getCommandList(viewType);
            break;
        }
        ui.printOutput(output);
    }
}
