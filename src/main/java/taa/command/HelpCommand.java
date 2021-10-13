package taa.command;

import taa.Ui;
import taa.exception.TaaException;
import taa.module.ModuleList;
import taa.storage.Storage;

public class HelpCommand extends Command {
    private static final String MESSAGE_FORMAT_OUTPUT = "Available commands:\n%s";

    private static final Command[] commands = {
        new AddAssessmentCommand(""),
        new AddModuleCommand(""),
        new AddStudentCommand(""),
        new AverageMarksCommand(""),
        new DeleteStudentCommand(""),
        new EditStudentCommand(""),
        new FindStudentCommand(""),
        new ExitCommand(""),
        new HelpCommand(""),
        new ListAssessmentsCommand(""),
        new ListAttendanceCommand(""),
        new ListMarksCommand(""),
        new ListModulesCommand(""),
        new ListStudentsCommand(""),
        new SetAttendanceCommand(""),
        new SetMarksCommand("")
    };

    public HelpCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

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
