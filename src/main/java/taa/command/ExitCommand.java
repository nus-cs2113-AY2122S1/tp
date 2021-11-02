package taa.command;

//@@author leyondlee
import taa.teachingclass.ClassList;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;

public class ExitCommand extends Command {
    private static final String MESSAGE_FORMAT_EXIT_USAGE = "%s";

    public ExitCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    /**
     * Executes the exit command and exits the program.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command.
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        ui.printExitMessage();
        this.isExit = true;
    }

    @Override
    protected String getUsage() {
        return String.format(MESSAGE_FORMAT_EXIT_USAGE, COMMAND_EXIT);
    }
}
