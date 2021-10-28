package taa.command.module;

//@@author leyondlee
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.ModuleList;

public class ListModulesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no modules in the list.";

    private static final String MESSAGE_FORMAT_OUTPUT = "Module list:\n%s";

    public ListModulesCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    /**
     * Executes the list_modules command and lists all the modules.
     *
     * @param moduleList The list of modules.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        String message;
        if (moduleList.getSize() == 0) {
            message = MESSAGE_LIST_EMPTY;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, moduleList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_GENERIC_USAGE,
            COMMAND_LIST_MODULES
        );
    }
}
