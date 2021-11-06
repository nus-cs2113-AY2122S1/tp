package taa.command.teachingclass;

//@@author leyondlee
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.logger.TaaLogger;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;

public class AddClassCommand extends Command {
    private static final String KEY_CLASS_ID = "i";
    private static final String KEY_CLASS_NAME = "n";
    private static final String[] ADD_CLASS_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_CLASS_NAME};

    private static final String MESSAGE_CLASS_EXISTS = "Class already exists.";

    private static final String MESSAGE_FORMAT_ADD_CLASS_USAGE = "%s %s/<CLASS_ID> [%s/<CLASS_NAME>]";
    private static final String MESSAGE_FORMAT_CLASS_ADDED = "Class added:\n  %s\nThere are %d classes in the list.";

    public AddClassCommand(String argument) {
        super(argument, ADD_CLASS_ARGUMENT_KEYS, true);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!argumentMap.containsKey(KEY_CLASS_ID)) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String classId = argumentMap.get(KEY_CLASS_ID);
        if (!TeachingClass.isValidId(classId)) {
            throw new TaaException(MESSAGE_INVALID_CLASS_ID);
        }
    }

    /**
     * Executes the add_class command and adds a class.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        assert argumentMap.containsKey(KEY_CLASS_ID);
        String classId = argumentMap.get(KEY_CLASS_ID);

        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass != null) {
            throw new TaaException(MESSAGE_CLASS_EXISTS);
        }

        String name = argumentMap.getOrDefault(KEY_CLASS_NAME, "");
        teachingClass = new TeachingClass(classId, name);
        classList.addClass(teachingClass);

        TaaLogger.LOGGER.logInfo(String.format("Added class: %s", teachingClass));

        storage.save(classList);
        ui.printMessage(String.format(MESSAGE_FORMAT_CLASS_ADDED, teachingClass, classList.getSize()));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_ADD_CLASS_USAGE,
            COMMAND_ADD_CLASS,
            KEY_CLASS_ID,
            KEY_CLASS_NAME
        );
    }
}
