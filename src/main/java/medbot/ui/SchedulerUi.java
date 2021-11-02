package medbot.ui;

public class SchedulerUi {
    private static final String END_LINE = System.lineSeparator();
    private static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "-------------------------------------------------- " + END_LINE;
    private static final String APPOINTMENT_TABLE_HEADER = " |  ID  |     Date/Time     | Patient ID |"
            + "     Patient Name     | Staff ID |      Staff Name      | " + END_LINE;

    public static String getAddSchedulerHelpMessage() {
        return "Adds an appointment to the list. MedBot will check if the appointment clashes with " + END_LINE
                + "others and display an error message if it does." + END_LINE
                + "Format: `add p/PATIENT_ID s/STAFF_ID d/DATE_TIME`" + END_LINE
                + "The format for `DATE_TIME` is `DDMMYY hhmm`. I.e. 9 February 2021, "
                + "0800HRS should be written as `090221 0800`" + END_LINE
                + "Do note that the appointments are managed at an hourly basis. For example, " + END_LINE
                + "any appointments set to any time between 0800HRS and 0859HRS will be treated as " + END_LINE
                + "an appointment from 0800HRS to 0859HRS. No subsequent appointment can then be scheduled " + END_LINE
                + "for either the patient and the medical staff during that window." + END_LINE;
    }

    public static String getEditSchedulerHelpMessage() {
        return "Edit an appointment’s information. MedBot will check if the edited appointment clashes with "
                + "others and display an error message if it does." + END_LINE
                + "Format: edit APPOINTMENT_ID [p/PATIENT_ID] [s/STAFF_ID] [d/DATE_TIME]" + END_LINE;
    }

    public static String getFindSchedulerHelpMessage() {
        return "Finds a person’s list of appointments. The list can be filtered by date-time to"
                + " display the list of appointments before/after a certain date." + END_LINE
                + "Format: find PERSON_TYPE/PERSON_ID [FILTER_TYPE/DATE_TIME]" + END_LINE
                + "The format for DATE_TIME is DDMMYY hhmm. I.e. 9 February 2021, "
                + "0800HRS should be written as 090221 0800" + END_LINE
                + "PERSON_TYPE is p (patient) or s (staff)" + END_LINE
                + "FILTER_TYPE is b (before) or a (after)" + END_LINE;
    }

    public static String getViewSchedulerHelpMessage() {
        return "View the information of an appointment." + END_LINE
                + "Format: view APPOINTMENT_ID" + END_LINE;
    }

    public static String getListSchedulerHelpMessage() {
        return "List the information of all appointments, including those of hidden patients." + END_LINE
                + "Format: list " + END_LINE;
    }

    public static String getDeleteSchedulerHelpMessage() {
        return "Delete an appointment from the list.\n" + END_LINE
                + "Format: delete APPOINTMENT_ID\n" + END_LINE;
    }

    public static String getNoAppointmentsFoundMessage() {
        return "Sorry, no appointments meeting that criteria were found!";
    }

    public static String getNoAppointmentsMessage() {
        return "Sorry, there are no appointments found.";
    }

    public static String getSchedulerCommandList() {
        return "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "get view" + END_LINE + "switch" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes. For " + END_LINE
                + "examples of the expected output, please refer to the actual user guide." + END_LINE;
    }

    public static String getFindAppointmentListMessage(String appointmentString) {
        return "Here is a list of matched appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString
                + TABLE_ROW_SEPARATOR;
    }

    public static String getListAppointmentListMessage(String appointmentString) {
        return "Here is a list of all appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString
                + TABLE_ROW_SEPARATOR;
    }

    public static String getAddScheduleMessage(int id) {
        return "Added schedule with schedule ID: " + id + END_LINE;
    }

    public static String getDeleteScheduleMessage(int id) {
        return "Schedule with id " + id + " deleted from system." + END_LINE;
    }

    public static String getEditScheduleMessage(int id, String info) {
        return "The information of schedule with ID " + id + " has been edited to:" + END_LINE + END_LINE
                + info + END_LINE;
    }

    public static String getCurrentViewMessage() {
        return "You are currently in the Scheduler's View.";
    }
}
