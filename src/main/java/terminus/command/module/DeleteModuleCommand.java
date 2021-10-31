package terminus.command.module;

import terminus.command.CommandResult;
import terminus.command.content.DeleteCommand;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageTypeEnum;

public class DeleteModuleCommand extends DeleteCommand {

    public DeleteModuleCommand() {
        super(null);
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) throws InvalidArgumentException {

        String[] listOfModule = moduleManager.getAllModules();
        if (!CommonUtils.isValidIndex(itemNumber, listOfModule)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        assert itemNumber > 0;
        moduleManager.removeModule(listOfModule[itemNumber - 1]);

        String message = String.format(Messages.MESSAGE_RESPONSE_MODULE_DELETE, listOfModule[itemNumber - 1]);
        CommandResult result = new CommandResult(null, StorageActionEnum.DELETE,
                StorageTypeEnum.FOLDER, message);
        result.setDeletedItemName(listOfModule[itemNumber - 1]);
        return result;
    }

}
