package medbot.command;

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
        if (secondCommand.equals(COMMAND_HELP)) {
            ui.printCommandList();
        } else if (secondCommand.equals(COMMAND_LIST)) {
            ui.printListHelpMessage();
        } else if (secondCommand.equals(COMMAND_VIEW)) {
            ui.printViewHelpMessage();
        } else if (secondCommand.equals(COMMAND_ADD)) {
            ui.printAddHelpMessage();
        } else if (secondCommand.equals(COMMAND_EDIT)) {
            ui.printEditHelpMessage();
        } else if (secondCommand.equals(COMMAND_DELETE)) {
            ui.printDeleteHelpMessage();
        } else if (secondCommand.equals(COMMAND_EXIT)) {
            ui.printExitHelpMessage();
        } else {
            ui.printUnrecognisedCommandHelpMessage();
        }
    }
}
