package terminus.command.module;

import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.ui.Ui;

public class AddModuleCommand extends Command {
    private String moduleName;
    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    @Override
    public String getFormat() {
        return "add \"<module name>\"";
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return "Adds a module";
    }

    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        if (arguments == null || arguments.isBlank()) {
            TerminusLogger.warning("Failed to parse arguments: arguments is empty");
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (!isValidModuleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }

        moduleName = argArray.get(0);
        if (!moduleName.matches("\\S+")) {
            throw new InvalidArgumentException("Module name cannot contain any whitespaces!");
        }
    }

    /**
     * Executes the command. Prints the required result to the Ui.
     *
     * @param ui     The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    @Override
    public CommandResult execute(Ui ui, ModuleManager moduleManager) throws InvalidCommandException, InvalidArgumentException {
        moduleManager.setModule(moduleName);
        ui.printSection(String.format("Module %s has been added",moduleName));
        return new CommandResult(true);
    }

    private boolean isValidModuleArguments (ArrayList<String> argArray) {
        if(argArray.size() != 1) {
            return false;
        } else {
            return !CommonUtils.hasEmptyString(argArray);
        }
    }
}

