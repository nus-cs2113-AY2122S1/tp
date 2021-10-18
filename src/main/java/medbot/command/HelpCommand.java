package medbot.command;

import medbot.Ui;
import medbot.list.PersonList;

public class HelpCommand extends Command {

    private CommandType commandType = null;

    public HelpCommand() {
    }

    public HelpCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public void execute(PersonList personList, Ui ui) {
        String output;
        if (commandType == null) {
            output = ui.getCommandList();
            ui.printOutput(output);
            return;
        }
        switch (commandType) {
        case ADD_PATIENT:
            output = ui.getAddHelpMessage();
            break;
        case DELETE_PATIENT:
            output = ui.getDeleteHelpMessage();
            break;
        case EDIT_PATIENT:
            output = ui.getEditHelpMessage();
            break;
        case EXIT:
            output = ui.getExitHelpMessage();
            break;
        case LIST_PATIENT:
            output = ui.getListHelpMessage();
            break;
        case SWITCH:
            output = ""; //todo
            break;
        case VIEW_PATIENT:
            output = ui.getViewHelpMessage();
            break;
        default:
            output = ui.getCommandList();
            break;
        }
        ui.printOutput(output);
    }
}
