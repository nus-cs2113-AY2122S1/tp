package taa.command.classcategory;

//@@author leyondlee
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.classmodel.ClassList;

public class ListClassesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no classes in the list.";

    private static final String MESSAGE_FORMAT_OUTPUT = "Class list:\n%s";

    public ListClassesCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    /**
     * Executes the list_classes command and lists all the classes.
     *
     * @param classList The ClassGroupList instance.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String message;
        if (classList.getSize() == 0) {
            message = MESSAGE_LIST_EMPTY;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, classList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_GENERIC_USAGE,
            COMMAND_LIST_CLASSES
        );
    }
}
