package taa.command.student;

//@@author hozhenhong99
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class SetCommentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_STUDENT_COMMENT = "t";
    private static final String[] SET_COMMENT_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_STUDENT_INDEX, KEY_STUDENT_COMMENT};

    private static final String MESSAGE_FORMAT_SET_COMMAND_USAGE = "%s %s/<MODULE_CODE> %s/<STUDENT_INDEX> "
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

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String comment = argumentMap.get(KEY_STUDENT_COMMENT);

        student.setComment(comment);

        storage.save(moduleList);
        ui.printMessage(String.format(MESSAGE_FORMAT_SET_COMMENT, student, comment));
    }

    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_SET_COMMAND_USAGE,
                COMMAND_SET_COMMENT,
                KEY_MODULE_CODE,
                KEY_STUDENT_INDEX,
                KEY_STUDENT_COMMENT
        );
    }
}