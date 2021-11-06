package taa.command.comment;

//@@author hozhenhong99
import taa.Ui;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.student.Student;

import java.util.ArrayList;

public class ListCommentsCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String[] LIST_ATTENDANCE_ARGUMENT_KEYS = {KEY_CLASS_ID};
    private static final String MESSAGE_LIST_COMMENT_HEADER = "Here is the list of all students and the comments:";

    private static final String MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE = "%s %s/<CLASS_ID>";

    public ListCommentsCommand(String argument) {
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
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        ArrayList<Student> students = teachingClass.getStudentList().getStudents();
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
            KEY_CLASS_ID
        );
    }
}
