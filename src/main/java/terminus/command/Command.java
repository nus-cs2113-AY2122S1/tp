package terminus.command;

import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;

public abstract class Command {

    protected String arguments;
    private String moduleName;

    /**
     * Returns the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    public abstract String getFormat();

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    public abstract String getHelpMessage();

    /**
     * Parses remaining arguments for the command.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    public void parseArguments(String arguments)
        throws InvalidArgumentException {
        if (!CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    /**
     * Executes the command.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException  when the command could not be found.
     * @throws InvalidArgumentException when arguments parsing fails.
     */
    public abstract CommandResult execute(ModuleManager moduleManager)
            throws InvalidCommandException, InvalidArgumentException;

    /**
     * Returns the module name.
     *
     * @return The String containing the module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the module name.
     *
     * @param moduleName The String containing the module name to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
