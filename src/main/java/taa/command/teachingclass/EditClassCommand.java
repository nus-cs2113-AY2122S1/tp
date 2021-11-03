package taa.command.teachingclass;

//@@author leyondlee
import taa.Ui;
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;

public class EditClassCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_NEW_CLASS_ID = "i";
    private static final String KEY_NEW_CLASS_NAME = "n";
    private static final String[] EDIT_CLASS_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_NEW_CLASS_ID,
        KEY_NEW_CLASS_NAME
    };

    private static final String MESSAGE_FORMAT_EDIT_CLASS_USAGE = "%s %s/<CLASS_ID> "
        + "[%s/<NEW_CLASS_ID> | %s/<NEW_CLASS_NAME>]";
    private static final String MESSAGE_FORMAT_CLASS_EDITED = "Class edited:\n  %s";
    private static final String MESSAGE_FORMAT_CLASS_EXISTS = "CLASS_ID: %s already exists. "
        + "Please use a different CLASS_ID.";

    public EditClassCommand(String argument) {
        super(argument, EDIT_CLASS_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!argumentMap.containsKey(KEY_CLASS_ID)) {
            throw new TaaException(getMissingArgumentMessage());
        }

        boolean hasNewClassId = argumentMap.containsKey(KEY_NEW_CLASS_ID);
        boolean hasNewClassName = argumentMap.containsKey(KEY_NEW_CLASS_NAME);
        boolean hasOptionalArguments = (hasNewClassId || hasNewClassName);
        if (!hasOptionalArguments) {
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

        assert (argumentMap.containsKey(KEY_NEW_CLASS_ID) || argumentMap.containsKey(KEY_NEW_CLASS_NAME));
        String newClassId = argumentMap.getOrDefault(KEY_NEW_CLASS_ID, null);
        if (newClassId != null) {
            TeachingClass existingTeachingClass = classList.getClassWithId(newClassId);
            boolean isSameTeachingClass = teachingClass == existingTeachingClass;
            if (existingTeachingClass != null && !isSameTeachingClass) {
                throw new TaaException(String.format(MESSAGE_FORMAT_CLASS_EXISTS, existingTeachingClass.getId()));
            }

            teachingClass.setId(newClassId);
        }

        String newClassName = argumentMap.getOrDefault(KEY_NEW_CLASS_NAME, null);
        if (newClassName != null) {
            teachingClass.setName(newClassName);
        }

        storage.save(classList);

        ui.printMessage(String.format(MESSAGE_FORMAT_CLASS_EDITED, teachingClass));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_EDIT_CLASS_USAGE,
            COMMAND_EDIT_CLASS,
            KEY_CLASS_ID,
            KEY_NEW_CLASS_ID,
            KEY_NEW_CLASS_NAME
        );
    }
}
