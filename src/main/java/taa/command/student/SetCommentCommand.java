package taa.command.student;

//@@author hozhenhong99
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.teachingclass.ClassList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class SetCommentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_STUDENT_COMMENT = "t";
    private static final String[] SET_COMMENT_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_STUDENT_INDEX, KEY_STUDENT_COMMENT};

    private static final String MESSAGE_FORMAT_SET_COMMAND_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> "
            + "%s/<KEY_STUDENT_COMMENT>";
    private static final String MESSAGE_FORMAT_SET_COMMENT = "Comment added to student:\n  %s | %s";

    public SetCommentCommand(String argument) {
        super(argument, SET_COMMENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
    }

    /**
     * Executes the set_comment command and sets the comment of a student.
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

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = teachingClass.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String comment = argumentMap.get(KEY_STUDENT_COMMENT);

        student.setComment(comment);

        storage.save(classList);
        ui.printMessage(String.format(MESSAGE_FORMAT_SET_COMMENT, student, comment));
    }

    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_SET_COMMAND_USAGE,
                COMMAND_SET_COMMENT,
            KEY_CLASS_ID,
                KEY_STUDENT_INDEX,
                KEY_STUDENT_COMMENT
        );
    }
}