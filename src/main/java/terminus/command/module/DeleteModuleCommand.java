package terminus.command.module;

import java.io.IOException;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.storage.ModuleStorage;
import terminus.ui.Ui;

public class DeleteModuleCommand extends Command {

    private int itemNumber;

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_DELETE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_MODULE_DELETE;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        TerminusLogger.info("Parsing delete arguments");
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Failed to parse delete itemNumber : %s", arguments));
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
        if (itemNumber <= 0) {
            TerminusLogger.warning(String.format("Invalid itemNumber : %d", itemNumber));
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_INVALID_NUMBER);
        }
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param ui The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException, IOException {
        String[] listOfModule = moduleManager.getAllModules();
        if (!isValidIndex(itemNumber, listOfModule)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        moduleManager.removeModule(listOfModule[itemNumber - 1]);

        // Delete all files and then its folder
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule(moduleManager, listOfModule[itemNumber - 1]);

        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_MODULE_DELETE, listOfModule[itemNumber - 1]));
        return new CommandResult(true);
    }

    /**
     * Returns a boolean if the index give is valid.
     *
     * @param index The index to check
     * @param listOfModule The full list of modules
     * @return True if the index is valid or else it is false
     */
    private boolean isValidIndex(int index, String[] listOfModule) {
        return listOfModule.length >= index && index > 0;
    }
}
