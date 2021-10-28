package taa.command;

import taa.Ui;
import taa.command.assessment.AddAssessmentCommand;
import taa.command.assessment.DeleteAssessmentCommand;
import taa.command.assessment.EditAssessmentCommand;
import taa.command.assessment.ListAssessmentsCommand;
import taa.command.attendance.DeleteAttendanceCommand;
import taa.command.attendance.ListAttendanceCommand;
import taa.command.attendance.SetAttendanceCommand;
import taa.command.mark.AverageMarksCommand;
import taa.command.mark.DeleteMarkCommand;
import taa.command.mark.EditMarkCommand;
import taa.command.mark.ListMarksCommand;
import taa.command.mark.MedianMarkCommand;
import taa.command.mark.SetMarkCommand;
import taa.command.module.AddModuleCommand;
import taa.command.module.DeleteModuleCommand;
import taa.command.module.EditModuleCommand;
import taa.command.module.ListModulesCommand;
import taa.command.student.AddStudentCommand;
import taa.command.student.DeleteStudentCommand;
import taa.command.student.EditStudentCommand;
import taa.command.student.FindStudentCommand;
import taa.command.student.ListCommentCommand;
import taa.command.student.ListStudentsCommand;
import taa.command.student.SetCommentCommand;
import taa.command.student.SortByScoresCommand;
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
        new ListCommentCommand(""),
        new SetCommentCommand(""),
        new DeleteStudentCommand(""),
        new SortByScoresCommand(""),
        new ListAssessmentsCommand(""),
        new AddAssessmentCommand(""),
        new EditAssessmentCommand(""),
        new DeleteAssessmentCommand(""),
        new ListMarksCommand(""),
        new SetMarkCommand(""),
        new EditMarkCommand(""),
        new DeleteMarkCommand(""),
        new AverageMarksCommand(""),
        new MedianMarkCommand(""),
        new ListAttendanceCommand(""),
        new SetAttendanceCommand(""),
        new DeleteAttendanceCommand(""),
        new ExitCommand(""),
        new HelpCommand(""),
        new ArchiveCommand(""),
        new ResetCommand("")
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
