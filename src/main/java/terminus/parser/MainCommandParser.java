package terminus.parser;

import static terminus.common.CommonUtils.getCurrentDay;

import terminus.command.GoCommand;
import terminus.command.TimetableCommand;
import terminus.command.module.ModuleCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.module.ModuleManager;
import terminus.timetable.Timetable;

public class MainCommandParser extends CommandParser {

    private static MainCommandParser parser;

    private MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        if (parser == null) {
            parser = new MainCommandParser();
            parser.addCommand(CommonFormat.COMMAND_MODULE, new ModuleCommand());
            parser.addCommand(CommonFormat.COMMAND_GO, new GoCommand());
            parser.addCommand(CommonFormat.COMMAND_TIMETABLE, new TimetableCommand());
        }
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        String mainReminder = getMainReminder(moduleManager);
        return Messages.MAIN_BANNER + mainReminder;
    }

    public String getMainReminder(ModuleManager moduleManager) {
        StringBuilder result = new StringBuilder();
        String currentDay = getCurrentDay();
        Timetable timetable = new Timetable(moduleManager);
        String dailySchedule = timetable.getDailySchedule(currentDay);

        if (dailySchedule != null) {
            result.append(Messages.MAIN_REMINDER);
            result.append(dailySchedule);
        } else {
            result.append(Messages.MESSAGE_EMPTY_DAILY_SCHEDULE);
        }
        return result.toString();
    }
}
