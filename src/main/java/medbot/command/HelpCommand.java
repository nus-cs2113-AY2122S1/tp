package medbot.command;

import medbot.Parser;
import medbot.PatientList;
import medbot.Ui;

public class HelpCommand extends Command {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_HELP = "help";

    private String secondCommand;

    public HelpCommand(String secondCommand) {
        this.secondCommand = secondCommand;
    }

    @Override
    public void execute(PatientList patientList, Ui ui) {
        String output;
        if (secondCommand.equals(COMMAND_HELP)) {
            output = ui.getCommandList();
        } else if (secondCommand.equals(COMMAND_LIST)) {
            output = ui.getListHelpMessage();
        } else if (secondCommand.equals(COMMAND_VIEW)) {
            output = ui.getViewHelpMessage();
        } else if (secondCommand.equals(COMMAND_ADD)) {
            output = ui.getAddHelpMessage();
        } else if (secondCommand.equals(COMMAND_EDIT)) {
            output = ui.getEditHelpMessage();
        } else if (secondCommand.equals(COMMAND_DELETE)) {
            output = ui.getDeleteHelpMessage();
        } else if (secondCommand.equals(COMMAND_EXIT)) {
            output = ui.getExitHelpMessage();
        } else {
            output = ui.getUnrecognisedCommandHelpMessage();
        }
        ui.printOutput(output);
    }
}
