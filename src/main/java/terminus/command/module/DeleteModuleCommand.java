package terminus.command.module;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
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
        return "Deletes a module";
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments == null || arguments.isBlank()) {
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
     * @param ui            The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException {
        if (!isValidIndex(itemNumber, moduleManager)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        String[] listOfModule = moduleManager.getAllModules();
        moduleManager.removeModule(listOfModule[itemNumber - 1]);
        ui.printSection(String.format("Deleted module %s.",listOfModule[itemNumber - 1]));
        return new CommandResult(true);
    }

    private boolean isValidIndex(int index, ModuleManager moduleManager) {
        String[] listOfModule = moduleManager.getAllModules();
        return listOfModule.length >= index && index > 0;
    }
}
