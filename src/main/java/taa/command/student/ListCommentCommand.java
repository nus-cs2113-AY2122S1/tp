package taa.command.student;

//@@author hozhenhong99
import taa.Ui;
import taa.attendance.AttendanceList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

import java.util.ArrayList;

public class ListCommentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] LIST_ATTENDANCE_ARGUMENT_KEYS = {KEY_MODULE_CODE};
    private static final String MESSAGE_LIST_COMMENT_HEADER = "Here is the list of all students and the comments:";

    private static final String MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE = "%s %s/<MODULE_CODE>";

    public ListCommentCommand(String argument) {
        super(argument, LIST_ATTENDANCE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    /**
     * Executes the list_comment command and lists all students along with their comments.
     *
     * @param moduleList The list of modules.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModuleWithCode(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        ArrayList<Student> students = module.getStudentList().getStudents();
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_LIST_COMMENT_HEADER);
        int studentIndex = 1;
        for (Student student : students) {
            stringBuilder.append("\n");
            stringBuilder.append(studentIndex);
            stringBuilder.append(". ");
            stringBuilder.append(student);
            stringBuilder.append(" | ");
            stringBuilder.append(student.getComment());
            studentIndex += 1;
        }
        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE,
                COMMAND_LIST_COMMENT,
                KEY_MODULE_CODE
        );
    }
}
