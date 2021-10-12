package terminus.command;

import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public abstract class Command {

    protected String arguments;

    public Command() {

    }

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
     * @throws InvalidArgumentException Exception for when arguments parsing fails.
     * @throws InvalidTimeFormatException Exception for when time format is invalid.
     */
    public void parseArguments(String arguments) throws InvalidArgumentException, InvalidTimeFormatException {
        this.arguments = arguments;
    }

    /**
     * Executes the command.
     * Prints the required result to the Ui.
     *
     * @param ui The Ui object to send messages to the users.
     * @param module The NusModule contain the ContentManager of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException Exception for when the command could not be found.
     * @throws InvalidArgumentException Exception for when arguments parsing fails.
     * @throws InvalidTimeFormatException Exception for when time format is invalid.
     */
    public abstract CommandResult execute(Ui ui, NusModule module)
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException;
}
