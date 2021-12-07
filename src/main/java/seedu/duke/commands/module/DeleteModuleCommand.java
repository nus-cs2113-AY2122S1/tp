package seedu.duke.commands.module;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ClickException;
import seedu.duke.exceptions.module.IllegalModuleIndexException;
import seedu.duke.module.ModuleList;
import seedu.duke.module.ModuleManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author nvbinh15

/**
 * A representation of the command for deleting a Module.
 */
public class DeleteModuleCommand extends Command {
    public static final String MESSAGE_DELETE_MODULE = "I have deleted this module:";
    String commandArgs;

    private static ModuleManager moduleManager = new ModuleManager();

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public DeleteModuleCommand() {
        syntax = "module delete [INDEX]";
    }

    /**
     * Class constructor.
     *
     * @param commandArgs The command arguments.
     */
    public DeleteModuleCommand(String commandArgs) {
        this.commandArgs = commandArgs;
        this.helpMessage = "Delete a listed module";
        this.syntax = "module delete [MODULE_INDEX]";
    }

    /**
     * Executes the DeleteModuleCommand.
     *
     * @param ui      The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws ClickException If there is an exception of type ClickException occurs.
     * @throws Exception If there is an exception of type other than ClickException occurs.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ClickException, Exception {
        int moduleIndex = Integer.parseInt(commandArgs.strip()) - 1;
        moduleManager.deleteModule(moduleIndex);
    }
}
