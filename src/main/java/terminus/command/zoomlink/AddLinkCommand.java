package terminus.command.zoomlink;

import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.ui.Ui;

import java.time.LocalTime;
import java.util.ArrayList;

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
     * @throws InvalidTimeFormatException Exception for when time format is invalid
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException, InvalidTimeFormatException {
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
            isValid = false;
        } else if (CommonFormat.isArrayEmpty(argArray)) {
            isValid = false;
        }
        return isValid;
    }
}
