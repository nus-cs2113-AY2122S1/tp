package terminus.timetable;

import terminus.common.DaysOfWeekEnum;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class Timetable {

    private int index = 0;

    public Timetable() {
    }

    /**
     * Lists all the schedule for a particular day.
     *
     * @param contentManager ContentManager object containing all user's links.
     * @return StringBuilder of all the schedules for the particular day.
     */
    public StringBuilder listDailySchedule(ContentManager<Link> contentManager, String currentDay) {
        StringBuilder dailySchedule = new StringBuilder();
        for (Link schedule : contentManager.getContents()) {
            if (schedule.getDay().equalsIgnoreCase(currentDay)) {
                index++;
                dailySchedule.append(String.format("%d. %s\n", index, schedule.getViewDescription()));
            }
        }
        return dailySchedule;
    }

    /**
     * Retrieve and format all the user's schedule for the particular day.
     *
     * @param result The string containing the retrieved user schedule.
     * @param moduleManager ModuleManager object containing all the module from which the schedules are retrieved.
     */
    public boolean getDailySchedule(StringBuilder result, ModuleManager moduleManager, String today) {
        String[] modules = moduleManager.getAllModules();

        for (String moduleName : modules) {
            NusModule module = moduleManager.getModule(moduleName);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            assert contentManager != null;
            result.append(listDailySchedule(contentManager, today));
            TerminusLogger.info(String.format("Successfully acquire %s's schedule for %s", moduleName, today));
        }
        index = 0;
        if (result.toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retrieve and format all the user's schedule for the week.
     *
     * @param result The string containing the retrieved user schedule.
     * @param moduleManager ModuleManager object containing all the module from which the schedules are retrieved.
     */
    public void getWeeklySchedule(StringBuilder result, ModuleManager moduleManager) {
        for (DaysOfWeekEnum currentDay : DaysOfWeekEnum.values()) {
            StringBuilder dailyResult = new StringBuilder();
            String today = currentDay.toString();
            if (getDailySchedule(dailyResult, moduleManager, today)) {
                assert dailyResult != null;
                String header = String.format("%s:\n", today);
                result.append(header.toUpperCase());
                result.append(dailyResult);
                assert result != null;
                TerminusLogger.info(String.format("Successfully acquire %s's schedule", today));
            }
            index = 0;
        }
    }

    /**
     * Print empty message for empty user schedule.
     *
     * @param result The string containing the retrieved user schedule.
     */
    public void checkEmptySchedule(StringBuilder result) {
        if (result.toString().isBlank()) {
            TerminusLogger.info("There is no schedule in the user's timetable");
            result.append(Messages.EMPTY_CONTENT_LIST_MESSAGE);
        }
    }
}
