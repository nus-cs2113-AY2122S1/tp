package terminus.command.module;

import java.io.IOException;
import java.util.ArrayList;
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
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageTypeEnum;

public class AddModuleCommand extends Command {

    private static final int MODULE_ARGS_COUNT = 1;
    private String moduleName;

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_MODULE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD_MODULE;
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            TerminusLogger.warning("Failed to parse arguments: arguments is empty");
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (!isValidModuleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        } else if (!CommonUtils.isValidFileName(argArray.get(0))) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_MODULE_NAME);
        } else if (!argArray.get(0).matches(CommonFormat.SPACE_NEGATED_DELIMITER)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MODULE_WHITESPACE);
        }
        moduleName = argArray.get(0).toUpperCase();
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager)
        throws InvalidCommandException, InvalidArgumentException {
        if (moduleManager.getModule(moduleName) != null) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_MODULE_EXIST);
        }

        moduleManager.addModule(moduleName);

        String message = String.format(Messages.MESSAGE_RESPONSE_MODULE_ADD, moduleName);
        return new CommandResult(moduleName, StorageActionEnum.CREATE, StorageTypeEnum.FOLDER, message);
    }

    private boolean isValidModuleArguments(ArrayList<String> argArray) {
        if (argArray.size() != MODULE_ARGS_COUNT) {
            return false;
        } else {
            return !CommonUtils.hasEmptyString(argArray);
        }
    }
}

