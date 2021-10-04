package terminus.command;

import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.CommandParser;
import terminus.ui.Ui;

public abstract class Command {

    public Command() {

    }

    /**
     * Contains the format for the command.
     *
     * @return The String object holding the appropriate format for the command.
     */
    public abstract String getFormat();

    /**
     * Contains the description for the command.
     *
     * @return The String object containing the description for this command.
     */
    public abstract String getHelpMessage();

    /**
     * The function that parse the remaining arguments for the command.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     */
    public abstract void parseArguments(String arguments);

    /**
     * A common function that needs to be run to execute the command.
     * This function also updates the required changes and prints UI.
     *
     * @param ui The Ui object to send messages to the users.
     * @param module The NusModule contain the list of all notes and schedules.
     * @return The CommandResult object indicating the success of failure including additional options.
     * @throws InvalidCommandException Exception for when the command could not be found.
     */
    public abstract CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException;

}
