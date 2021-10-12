package terminus.command.zoomlink;

import static terminus.common.CommonFormat.isValidDay;
import static terminus.common.CommonFormat.isValidUrl;

import java.time.LocalTime;
import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

/**
 * AddLinkCommand class which will manage the adding of new Links from user command.
 */
public class AddLinkCommand extends Command {

    private String description;
    private String day;
    private LocalTime startTime;
    private String link;

    private static final int ADD_SCHEDULE_ARGUMENTS = 4;

    public AddLinkCommand() {

    }

    /**
     * Returns the command format to Add a Link.
     *
     * @return The string containing the command format to add a link
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_SCHEDULE_FORMAT;
    }

    /**
     * Returns the description of Add Link Command.
     *
     * @return The string containing the description of an add command
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments in an add link command to its respective description, day, start-time, and link.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException Exception for when argument parsing fails
     * @throws InvalidArgumentException Exception for when any argument is invalid
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        // Perform required checks with regex
        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        ArrayList<String> argArray = CommonFormat.findArguments(arguments);
        if (!isValidScheduleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        String userStartTime = argArray.get(2);

        this.description = argArray.get(0);
        this.day = argArray.get(1);
        this.startTime = CommonFormat.convertToLocalTime(userStartTime);
        this.link = argArray.get(3);

        if (!isValidDay(this.day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", this.day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, this.day));
        }
        if (!isValidUrl(this.link)) {
            TerminusLogger.warning(String.format("Invalid Link: %s", this.link));
            throw new InvalidArgumentException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_LINK, this.link));
        }
        TerminusLogger.info(String.format("Parsed arguments (description = %s, day = %s, startTime = %s, link = %s)"
                + " to Add Link Command", description, day, startTime, link));
    }

    /**
     * Executes the add link command.
     * Prints the relevant response to the Ui.
     *
     * @param ui     The Ui object to send messages to the users.
     * @param module The NusModule contain the list of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution
     * @throws InvalidCommandException Exception for when the user command is not found
     * @throws InvalidArgumentException Exception for when the argument parsing fails
     */
    @Override
    public CommandResult execute(Ui ui, NusModule module) throws InvalidCommandException, InvalidArgumentException {
        ContentManager contentManager = module.getContentManager(Link.class);
        assert contentManager != null;

        contentManager.add(new Link(description, day, startTime, link));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_SCHEDULE, description));
        return new CommandResult(true, false);
    }

    /**
     * Checks if arguments are non-empty and valid.
     *
     * @param argArray The command arguments in an array list
     * @return True if the appropriate number of arguments are present, false otherwise.
     */
    private boolean isValidScheduleArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_SCHEDULE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments, %d arguments found",
                    ADD_SCHEDULE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            TerminusLogger.warning("Failed to parse arguments, some arguments found is empty");
            isValid = false;
        }
        return isValid;
    }
}
