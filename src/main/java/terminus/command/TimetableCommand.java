package terminus.command;

import static terminus.common.CommonUtils.isStringNullOrEmpty;
import static terminus.common.CommonUtils.isValidDay;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;
import terminus.timetable.Timetable;

public class TimetableCommand extends Command {

    private String day;

    /**
     * Returns the format of the command.
     *
     * @return The string object holding the appropriate format for the timetable command.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_TIMETABLE_FORMAT;
    }

    /**
     * Returns the description for the command.
     *
     * @return The String object containing the description for the timetable command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_TIMETABLE;
    }

    /**
     * Parses remaining arguments for the timetable command.
     *
     * @param arguments The string arguments to be parsed in to the respective fields.
     * @throws InvalidArgumentException when arguments are invalid.
     */
    @Override
    public void parseArguments(String arguments) throws InvalidArgumentException {
        day = arguments;
        if (!isStringNullOrEmpty(day) && !isValidDay(day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, day));
        }
        TerminusLogger.info(String.format("Parsed arguments (day = %s) to Timetable Command", day));
    }

    /**
     * Executes the timetable command. Prints the relevant response to the Ui.
     *
     * @param moduleManager The NusModule contain the ContentManager of all notes and schedules.
     * @return CommandResult to indicate the success and additional information about the execution.
     */
    @Override
    public CommandResult execute(ModuleManager moduleManager) {
        StringBuilder result = new StringBuilder();
        Timetable timetable = new Timetable(moduleManager);
        String schedule;

        if (isStringNullOrEmpty(day)) {
            schedule = timetable.getWeeklySchedule();
        } else {
            assert day != null;
            schedule = timetable.getDailySchedule(day);
        }
        result.append(timetable.checkEmptySchedule(schedule, day));
        return new CommandResult(result.toString());
    }
}
