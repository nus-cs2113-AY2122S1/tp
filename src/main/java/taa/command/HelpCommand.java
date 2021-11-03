package taa.command;

//@@author leyondlee
import taa.Ui;
import taa.teachingclass.ClassList;
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
import taa.command.teachingclass.AddClassCommand;
import taa.command.teachingclass.DeleteClassCommand;
import taa.command.teachingclass.EditClassCommand;
import taa.command.teachingclass.ListClassesCommand;
import taa.command.student.AddStudentCommand;
import taa.command.student.DeleteStudentCommand;
import taa.command.student.EditStudentCommand;
import taa.command.student.FindStudentCommand;
import taa.command.student.ListCommentCommand;
import taa.command.student.ListStudentsCommand;
import taa.command.student.SetCommentCommand;
import taa.command.student.SortByScoresCommand;
import taa.exception.TaaException;
import taa.storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HelpCommand extends Command {
    private static final String STRING_CLASS = "Class";
    private static final String STRING_STUDENT = "Student";
    private static final String STRING_ASSESSMENT = "Assessment";
    private static final String STRING_MARK = "Mark";
    private static final String STRING_ATTENDANCE = "Attendance";
    private static final String STRING_OTHERS = "Others";

    private static final String MESSAGE_OUTPUT_HEADER = "Available commands:";

    private static final LinkedHashMap<String, ArrayList<String>> commandUsages = new LinkedHashMap<>();

    public HelpCommand(String argument) {
        super(argument);
        initCommandUsages();
    }

    private static void initCommandUsages() {
        if (!commandUsages.isEmpty()) {
            return;
        }

        Command[] classCommands = {
            new ListClassesCommand(""),
            new AddClassCommand(""),
            new EditClassCommand(""),
            new DeleteClassCommand(""),
        };
        commandUsages.put(STRING_CLASS, getUsagesFromCommands(classCommands));

        Command[] studentCommands = {
            new ListStudentsCommand(""),
            new AddStudentCommand(""),
            new EditStudentCommand(""),
            new DeleteStudentCommand(""),
            new FindStudentCommand(""),
            new ListCommentCommand(""),
            new SetCommentCommand(""),
            new SortByScoresCommand(""),
        };
        commandUsages.put(STRING_STUDENT, getUsagesFromCommands(studentCommands));

        Command[] assessmentCommands = {
            new ListAssessmentsCommand(""),
            new AddAssessmentCommand(""),
            new EditAssessmentCommand(""),
            new DeleteAssessmentCommand("")
        };
        commandUsages.put(STRING_ASSESSMENT, getUsagesFromCommands(assessmentCommands));

        Command[] markCommands = {
            new ListMarksCommand(""),
            new SetMarkCommand(""),
            new EditMarkCommand(""),
            new DeleteMarkCommand(""),
            new AverageMarksCommand(""),
            new MedianMarkCommand(""),
        };
        commandUsages.put(STRING_MARK, getUsagesFromCommands(markCommands));

        Command[] attendanceCommands = {
            new ListAttendanceCommand(""),
            new SetAttendanceCommand(""),
            new DeleteAttendanceCommand("")
        };
        commandUsages.put(STRING_ATTENDANCE, getUsagesFromCommands(attendanceCommands));

        Command[] othersCommands = {
            new ExitCommand(""),
            new HelpCommand(""),
            new ArchiveCommand(""),
            new ResetCommand("")
        };
        commandUsages.put(STRING_OTHERS, getUsagesFromCommands(othersCommands));
    }

    private static ArrayList<String> getUsagesFromCommands(Command[] commands) {
        ArrayList<String> usages = new ArrayList<>();
        for (Command command : commands) {
            usages.add(command.getUsage());
        }

        return usages;
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    private String getStringForCommandGroup(String name) {
        if (!commandUsages.containsKey(name)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(name);
        stringBuilder.append(":");
        for (String string : commandUsages.get(name)) {
            stringBuilder.append("\n");
            stringBuilder.append("  - ");
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }

    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_OUTPUT_HEADER);
        for (String name : commandUsages.keySet()) {
            stringBuilder.append("\n\n");
            stringBuilder.append(getStringForCommandGroup(name));
        }

        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_GENERIC_USAGE,
            COMMAND_HELP
        );
    }
}
