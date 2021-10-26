package taa.command;

import taa.Ui;
import taa.exception.TaaException;
import taa.module.ModuleList;
import taa.storage.Storage;

public class HelpCommand extends Command {
    private static final String MESSAGE_FORMAT_OUTPUT = "Available commands:\n%s";

    private static final Command[] commands = {
        new ListModulesCommand(""),
        new AddModuleCommand(""),
        new EditModuleCommand(""),
        new DeleteModuleCommand(""),
        new ListStudentsCommand(""),
        new AddStudentCommand(""),
        new EditStudentCommand(""),
        new DeleteStudentCommand(""),
        new FindStudentCommand(""),
        new ListAssessmentsCommand(""),
        new AddAssessmentCommand(""),
        new EditAssessmentCommand(""),
        new DeleteAssessmentCommand(""),
        new ListMarksCommand(""),
        new SetMarkCommand(""),
        new EditMarkCommand(""),
        new DeleteMarkCommand(""),
        new AverageMarksCommand(""),
        new ListAttendanceCommand(""),
        new SetAttendanceCommand(""),
        new DeleteAttendanceCommand(""),
        new ExitCommand(""),
        new HelpCommand("")
    };

    public HelpCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commands.length; i += 1) {
            Command command = commands[i];

            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append("- ");
            stringBuilder.append(command.getUsage());
        }

        String message = String.format(MESSAGE_FORMAT_OUTPUT, stringBuilder.toString());
        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_GENERIC_USAGE,
            COMMAND_HELP
        );
    }
}
