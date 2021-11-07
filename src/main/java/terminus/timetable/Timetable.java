package terminus.timetable;

import static terminus.common.CommonUtils.isStringNullOrEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import terminus.common.DaysOfWeekEnum;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class Timetable {

    private ModuleManager moduleManager;
    private int index = 0;

    public Timetable(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    /**
     * Lists all the schedule for a particular day.
     *
     * @param contentManager ContentManager object containing all user's links.
     * @param currentDay     The particular day at which the schedules are selected from.
     * @return String String object containing all the schedules for the particular day.
     */
    private ArrayList<Link> listDailySchedule(ContentManager<Link> contentManager, String currentDay) {
        ArrayList<Link> dailySchedule = new ArrayList<>();

        contentManager.getContents()
                .stream()
                .filter(x -> x.getDay().equalsIgnoreCase(currentDay))
                .sorted(Comparator.comparing(Link::getStartTime))
                .forEach(x -> dailySchedule.add(x));

        return dailySchedule;
    }

    /**
     * Retrieve and format all the user's schedule for the particular day.
     *
     * @param today The particular day at which the schedules are selected from.
     * @return String String object containing all the schedules for the day
     */
    public String getDailySchedule(String today) {
        StringBuilder schedule = new StringBuilder();
        String[] modules = moduleManager.getAllModules();
        ArrayList<Link> dailySchedule = new ArrayList<>();
        Stream<String> stream = Arrays.stream(modules);

        stream.forEach(x -> {
            NusModule module = moduleManager.getModule(x);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            assert contentManager != null;
            dailySchedule.addAll(listDailySchedule(contentManager, today));
            TerminusLogger.info(String.format("Successfully acquire %s's schedule for %s", x, today));
        });

        dailySchedule.stream()
                .sorted(Comparator.comparing(Link::getStartTime))
                .forEach(x -> {
                    index++;
                    schedule.append(String.format("%d. %s\n", index, x.getViewDescription()));
                });

        index = 0;
        if (isStringNullOrEmpty(schedule.toString())) {
            return null;
        }
        return schedule.toString();
    }

    /**
     * Retrieve and format all the user's schedule for the week.
     *
     * @return String string object containing all the user's schedule for the week
     */
    public String getWeeklySchedule() {
        StringBuilder dailyResult = new StringBuilder();

        for (DaysOfWeekEnum currentDay : DaysOfWeekEnum.values()) {
            String today = currentDay.toString();
            String dailySchedule = getDailySchedule(today);
            if (!isStringNullOrEmpty(dailySchedule)) {
                assert dailySchedule != null;
                String header = String.format("%s:\n", today);
                dailyResult.append(header.toUpperCase());
                dailyResult.append(dailySchedule);
                assert dailyResult != null;
                TerminusLogger.info(String.format("Successfully acquire %s's schedule", today));
            }
            index = 0;
        }

        if (isStringNullOrEmpty(dailyResult.toString())) {
            return null;
        }
        return dailyResult.toString();
    }

    /**
     * Print empty message for empty user schedule.
     *
     * @param schedule The string containing the retrieved user schedule.
     * @param day      The day corresponding to the retrieved schedule
     */
    public String checkEmptySchedule(String schedule, String day) {
        if (schedule == null && day != null) {
            TerminusLogger.info("There is no schedule in the user's timetable");
            schedule = String.format(Messages.EMPTY_SCHEDULE_FOR_THE_DAY, day);
        } else if (schedule == null && day == null) {
            TerminusLogger.info("There is no schedule in the user's timetable");
            schedule = Messages.EMPTY_SCHEDULE_FOR_THE_WEEK;
        }
        return schedule;
    }
}
