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

    public String getFormat() {
        return CommonFormat.COMMAND_TIMETABLE_FORMAT;
    }

    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_TIMETABLE;
    }

    public void parseArguments(String arguments) throws InvalidArgumentException {
        day = arguments;
        if (!isStringNullOrEmpty(day) && !isValidDay(day)) {
            TerminusLogger.warning(String.format("Invalid Day: %s", day));
            throw new InvalidArgumentException(String.format(Messages.ERROR_MESSAGE_INVALID_DAY, day));
        }
    }

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

    public void getDailySchedule(StringBuilder result, ModuleManager moduleManager) {
        String[] modules = moduleManager.getAllModules();
        for (String moduleName : modules) {
            NusModule module = moduleManager.getModule(moduleName);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            result.append(listDailySchedule(contentManager));
        }
    }

    public void getWeeklySchedule(StringBuilder result, ModuleManager moduleManager) {
        for (DaysOfWeekEnum currentDay : DaysOfWeekEnum.values()) {
            day = currentDay.toString();
            String header = String.format("%s:\n", day);
            result.append(header);
            getDailySchedule(result, moduleManager);
        }
    }

    public void checkEmptySchedule(StringBuilder result) {
        if (result.toString().isBlank()) {
            result.append(Messages.EMPTY_CONTENT_LIST_MESSAGE);
        }
    }

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
