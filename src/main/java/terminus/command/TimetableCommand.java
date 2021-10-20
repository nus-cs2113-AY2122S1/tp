package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.DaysOfWeekEnum;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.ui.Ui;

import static terminus.common.CommonUtils.isStringNullOrEmpty;
import static terminus.common.CommonUtils.isValidDay;

public class TimetableCommand extends Command {
    private String day;

    public TimetableCommand() {

    }

    /**
     * Returns the format of the command.
     *
     * @return The string object holding the appropriate format for the timetable command
     */
    public String getFormat() {
        return CommonFormat.COMMAND_TIMETABLE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for the timetable command.
     */
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_TIMETABLE;
    }

    /**
     * Parses remaining arguments for the timetable command.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are invalid
     */
    public void parseArguments(String arguments) throws InvalidArgumentException {
        day = arguments;
        if (!isStringNullOrEmpty(day) && !isValidDay(day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, day));
        }
    }

    /**
     * Lists all the schedule for a particular day.
     *
     * @param contentManager ContentManager object containing all user's links
     * @return StringBuilder of all the schedules for the particular day
     */
    public StringBuilder listDailySchedule(ContentManager<Link> contentManager) {
        StringBuilder dailySchedule = new StringBuilder();
        int i = 0;
        for (Link schedule : contentManager.getContents()) {
            if (schedule.getDay().equalsIgnoreCase(day)) {
                i++;
                dailySchedule.append(String.format("%d. %s\n", i, schedule.getViewDescription()));
            }
        }
        return dailySchedule;
    }

    /**
     * Retrieve and format all the user's schedule for the particular day.
     *
     * @param result The string containing the retrieved user schedule
     * @param moduleManager ModuleManager object containing all the module from which the schedules are retrieved
     */
    public void getDailySchedule(StringBuilder result, ModuleManager moduleManager) {
        String[] modules = moduleManager.getAllModules();
        for (String moduleName : modules) {
            NusModule module = moduleManager.getModule(moduleName);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            if (listDailySchedule(contentManager).length() != 0) {
                String header = String.format("%s:\n", day);
                result.append(header.toUpperCase());
                result.append(listDailySchedule(contentManager));
            }
        }
    }

    /**
     * Retrieve and format all the user's schedule for the week.
     *
     * @param result The string containing the retrieved user schedule
     * @param moduleManager ModuleManager object containing all the module from which the schedules are retrieved
     */
    public void getWeeklySchedule(StringBuilder result, ModuleManager moduleManager) {
        for (DaysOfWeekEnum currentDay : DaysOfWeekEnum.values()) {
            day = currentDay.toString();
            getDailySchedule(result, moduleManager);
        }
    }

    /**
     * Print empty message for empty user schedule.
     *
     * @param result The string containing the retrieved user schedule
     */
    public void checkEmptySchedule(StringBuilder result) {
        if (result.toString().isBlank()) {
            result.append(Messages.EMPTY_CONTENT_LIST_MESSAGE);
        }
    }

    /**
     * Executes the timetable command. Prints the relevant response to the Ui.
     *
     * @param ui The Ui object to send messages to the users.
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    public CommandResult execute(Ui ui, ModuleManager moduleManager) {
        StringBuilder result = new StringBuilder();

        if (isStringNullOrEmpty(day)) {
            getWeeklySchedule(result, moduleManager);
        } else {
            getDailySchedule(result, moduleManager);
        }

        checkEmptySchedule(result);
        ui.printSection(result.toString());
        return new CommandResult(true, false);
    }
}
