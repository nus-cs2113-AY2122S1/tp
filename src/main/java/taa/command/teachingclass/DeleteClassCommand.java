package taa.command.teachingclass;

//@@author leyondlee
import taa.Ui;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;

public class DeleteClassCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String[] DELETE_CLASS_ARGUMENT_KEYS = {KEY_CLASS_ID};

    private static final String MESSAGE_FORMAT_DELETE_CLASS_USAGE = "%s %s/<CLASS_ID>";
    private static final String MESSAGE_FORMAT_DELETE_FAILED = "Fail to delete class:\n  %s";
    private static final String MESSAGE_FORMAT_CLASS_DELETED = "Class deleted:\n  %s";

    public DeleteClassCommand(String argument) {
        super(argument, DELETE_CLASS_ARGUMENT_KEYS);
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

    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        assert argumentMap.containsKey(KEY_CLASS_ID);
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String message;
        boolean isSuccessful = classList.deleteClass(teachingClass);
        if (isSuccessful) {
            message = String.format(MESSAGE_FORMAT_CLASS_DELETED, teachingClass);
        } else {
            message = String.format(MESSAGE_FORMAT_DELETE_FAILED, teachingClass);
        }

        storage.save(classList);
        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(MESSAGE_FORMAT_DELETE_CLASS_USAGE, COMMAND_DELETE_CLASS, KEY_CLASS_ID);
    }
}
