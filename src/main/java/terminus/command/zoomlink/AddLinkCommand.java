package terminus.command.zoomlink;

import static terminus.common.CommonUtils.isValidDay;
import static terminus.common.CommonUtils.isValidUrl;

import java.time.LocalTime;
import java.util.ArrayList;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.common.CommonUtils;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;

import terminus.exception.InvalidArgumentException;

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

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_SCHEDULE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments to the AddLinkCommand object.
     * The arguments are attributes for a new Link object.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are empty or missing.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {

        if (arguments == null || arguments.isBlank()) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }

        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (!isValidScheduleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }
        String userStartTime = argArray.get(2);

        this.description = argArray.get(0);
        this.day = argArray.get(1);
        this.startTime = CommonUtils.convertToLocalTime(userStartTime);
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
     * @param ui The Ui object to send messages to the users.
     * @param module The NusModule contain the list of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    @Override
    public CommandResult execute(Ui ui, NusModule module) {
        ContentManager contentManager = module.getContentManager(Link.class);
        assert contentManager != null;

        contentManager.add(new Link(description, day, startTime, link));
        ui.printSection(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_SCHEDULE, description));
        return new CommandResult(true, false);
    }

    /**
     * Checks if arguments are non-empty and valid.
     *
     * @param argArray The command arguments in an array list.
     * @return True if the appropriate number of arguments are present, false otherwise.
     */
    private boolean isValidScheduleArguments(ArrayList<String> argArray) {
        boolean isValid = true;
        if (argArray.size() != ADD_SCHEDULE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments, %d arguments found",
                    ADD_SCHEDULE_ARGUMENTS, argArray.size()));
            isValid = false;
        } else if (CommonUtils.hasEmptyString(argArray)) {
            TerminusLogger.warning("Failed to parse arguments, some arguments found is empty");
            isValid = false;
        }
        return isValid;
    }
}
