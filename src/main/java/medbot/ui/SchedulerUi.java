package medbot.ui;

import java.util.List;

public class SchedulerUi {
    public static final String END_LINE = System.lineSeparator();
    public static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    public static String getAddSchedulerHelpMessage() {
        return " " + END_LINE;
    }

    public static String getEditSchedulerHelpMessage() {
        return " " + END_LINE;
    }

    public static String getFindSchedulerHelpMessage() {
        return " " + END_LINE;
    }

    public static String getViewSchedulerHelpMessage() {
        return " " + END_LINE;
    }

    public static String getListSchedulerHelpMessage() {
        return " " + END_LINE;
    }

    public static String getDeleteSchedulerHelpMessage() {
        return " " + END_LINE;
    }


    public static String getScheduleInfo(String scheduleInfo) {
        return "Here's the requested patient:" + END_LINE + END_LINE
                + scheduleInfo + END_LINE;
    }


    public static String getAddSchedulerMessage(int schedulerId) {
        assert schedulerId > 0;
        return "Added schedule with schedule ID: " + schedulerId + END_LINE;
    }

    public String getDeleteScheduleMessage(int scheduleId) {
        assert scheduleId > 0;
        return "Schedule with id " + scheduleId + " has been deleted from system." + END_LINE;
    }

    public String getEditScheduleMessage(int scheduleId, String scheduleInfo) {
        assert scheduleId > 0;
        return "The information of patient with ID " + scheduleId + " has been edited to:" + END_LINE + END_LINE
                + scheduleInfo + END_LINE;
    }

    public String getFindSchedulesMessage(List<String> schedules) {
        if (schedules.size() == 0) {
            return "There is no patient with such attributes." + END_LINE;
        }
        String output = TABLE_ROW_SEPARATOR;
        for (String patient : schedules) {
            output += patient;
        }
        output += END_LINE;
        output += TABLE_ROW_SEPARATOR;
        return output;
    }

}
