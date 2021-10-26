package medbot.ui;

import java.util.List;

public class SchedulerUi {
    private static final String END_LINE = System.lineSeparator();
    private static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "-------------------------------------------------- " + END_LINE;
    private static final String APPOINTMENT_TABLE_HEADER = " |  ID  |     Date/Time     | Patient ID |"
            + "     Patient Name     | Staff ID |      Staff Name      | " + END_LINE;

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

    public static String getNoAppointmentsFoundMessage() {
        return "Sorry, no appointments meeting that criteria were found!";
    }

    public static String getNoAppointmentsMessage() {
        return "Sorry, there are no appointments found.";
    }

    /**
     * Returns a schedule information.
     *
     * @param scheduleInfo the Info of the patient to be printed.
     * @return the Schedule information
     */
    public static String getScheduleInfo(String scheduleInfo) {
        return "Here's the requested schedule:" + END_LINE + END_LINE
                + scheduleInfo + END_LINE;
    }

    public static String getSchedulerCommandList() {
        return "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "get view" + END_LINE + "exit" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + END_LINE;
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
        return "The information of schedule with ID " + scheduleId + " has been edited to:" + END_LINE + END_LINE
                + scheduleInfo + END_LINE;
    }

    public String getFindSchedulesMessage(List<String> schedules) {
        if (schedules.size() == 0) {
            return "There is no schedule with such attributes." + END_LINE;
        }
        String output = TABLE_ROW_SEPARATOR;
        for (String patient : schedules) {
            output += patient;
        }
        output += END_LINE;
        output += TABLE_ROW_SEPARATOR;
        return output;
    }

    public static String getFindAppointmentListMessage(String appointmentString) {
        return "Here is a list of matched appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString;
    }

    public static String getListAppointmentListMessage(String appointmentString) {
        return "Here is a list of all appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString;
    }

}
