package terminus.command.content.link;

import static terminus.common.CommonUtils.hasDurationOverflow;
import static terminus.common.CommonUtils.isValidDay;
import static terminus.common.CommonUtils.isValidDuration;
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
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.timetable.ConflictManager;


/**
 * AddLinkCommand class which will manage the adding of new Links from user command.
 */
public class AddLinkCommand extends Command {

    private String description;
    private String day;
    private LocalTime startTime;
    private int duration;
    private String link;

    private static final int ADD_SCHEDULE_ARGUMENTS = 5;

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_ADD_SCHEDULE_FORMAT;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_ADD;
    }

    /**
     * Parses the arguments to the AddLinkCommand object. The arguments are attributes for a new Link object.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are empty or missing.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {

        if (CommonUtils.isStringNullOrEmpty(arguments)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }

        ArrayList<String> argArray = CommonUtils.findArguments(arguments);
        if (hasMissingScheduleArguments(argArray)) {
            throw new InvalidArgumentException(this.getFormat(), Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        }

        this.description = argArray.get(0).trim();
        this.day = argArray.get(1).trim();
        String userStartTime = argArray.get(2).trim();
        this.startTime = CommonUtils.convertToLocalTime(userStartTime);

        try {
            this.duration = Integer.parseInt(argArray.get(3).trim());
        } catch (NumberFormatException e) {
            TerminusLogger.warning(String.format("Invalid Duration"));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DURATION_FORMAT));
        }

        this.link = argArray.get(4).trim();
        checkValidArguments();
        TerminusLogger.info(String.format("Parsed arguments (description = %s, day = %s, startTime = %s, link = %s)"
            + " to Add Link Command", description, day, startTime, link));
    }

    /**
     * Executes the add link command. Prints the relevant response to the Ui.
     *
     * @param moduleManager The NusModule contain the list of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) {
        assert getModuleName() != null;
        NusModule module = moduleManager.getModule(getModuleName());
        ContentManager<Link> contentManager = module.getContentManager(Link.class);
        assert contentManager != null;

        Link newLink = new Link(description, day, startTime, duration, link);
        ConflictManager scheduleConflict = new ConflictManager(moduleManager, newLink);

        StringBuilder stringBuilder = new StringBuilder();

        if (!CommonUtils.isStringNullOrEmpty(scheduleConflict.getConflictingSchedule())) {
            String conflicts = scheduleConflict.getConflictingSchedule();
            stringBuilder.append(Messages.MESSAGE_CONFLICTING_SCHEDULE + "\n").append(conflicts).append("\n");
        }
        contentManager.add(newLink);
        stringBuilder.append(String.format(Messages.MESSAGE_RESPONSE_ADD, CommonFormat.COMMAND_SCHEDULE, description));

        return new CommandResult(stringBuilder.toString());
    }

    /**
     * Checks if the proper amount of arguments exists and each are non-empty.
     *
     * @param argArray The command arguments in an array list.
     * @return True if the appropriate number of arguments are present, false otherwise.
     */
    private boolean hasMissingScheduleArguments(ArrayList<String> argArray) {
        boolean hasMissing = false;
        if (argArray.size() != ADD_SCHEDULE_ARGUMENTS) {
            TerminusLogger.warning(String.format("Failed to find %d arguments, %d arguments found",
                ADD_SCHEDULE_ARGUMENTS, argArray.size()));
            hasMissing = true;
        } else if (CommonUtils.hasEmptyString(argArray)) {
            TerminusLogger.warning("Failed to parse arguments, some arguments found is empty");
            hasMissing = true;
        }
        return hasMissing;
    }

    /**
     * Checks the validity of the arguments for a new Link object.
     *
     * @throws InvalidArgumentException when arguments are invalid
     */
    private void checkValidArguments() throws InvalidArgumentException {
        if (!isValidDay(this.day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", this.day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, this.day));
        } else if (!isValidDuration(this.duration)) {
            TerminusLogger.warning(String.format("Invalid Duration: %d", this.duration));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DURATION, this.duration));
        } else if (hasDurationOverflow(startTime, this.duration)) {
            TerminusLogger.warning(String.format("Invalid Duration: %d", this.duration));
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_SCHEDULE_OVERFLOW);
        } else if (!isValidUrl(this.link)) {
            TerminusLogger.warning(String.format("Invalid Link: %s", this.link));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_LINK, this.link));
        }
    }
}
