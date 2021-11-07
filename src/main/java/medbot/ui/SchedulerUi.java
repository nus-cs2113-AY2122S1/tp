package medbot.ui;

public class SchedulerUi {
    private static final String END_LINE = System.lineSeparator();
    private static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "-------------------------------------------------- " + END_LINE;
    private static final String APPOINTMENT_TABLE_HEADER = " |  ID  |     Date/Time     | Patient ID |"
            + "     Patient Name     | Staff ID |      Staff Name      | " + END_LINE;


    //@@author Kureans
    public static String getSchedulerCommandList() {
        return "Here is the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "delete" + END_LINE
                + "edit" + END_LINE
                + "view" + END_LINE
                + "list" + END_LINE
                + "find" + END_LINE
                + "switch" + END_LINE
                + "get view" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To view more information about each command and their respective command formats, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the actual user guide." + END_LINE;
    }
    //@@author

    /**
     * Returns a message String with help about the add appointment command.
     *
     * @return String with help about the add appointment command
     */
    public static String getAddAppointmentHelpMessage() {
        return "Adds an appointment to the list. MedBot will check if the appointment clashes with " + END_LINE
                + "others and display an error message if it does." + END_LINE
                + "Format: `add p/PATIENT_ID s/STAFF_ID d/DATE_TIME`" + END_LINE
                + "The format for `DATE_TIME` is `DDMMYY hhmm`." + END_LINE
                + "E.g., 9 February 2021, 0800HRS should be written as `090221 0800`" + END_LINE
                + "Do note that the appointments are managed at an hourly basis. For example, " + END_LINE
                + "any appointments set to any time between 0800HRS and 0859HRS will be treated as " + END_LINE
                + "an appointment from 0800HRS to 0859HRS. No subsequent appointment can then be scheduled " + END_LINE
                + "for either the patient and the medical staff during that window." + END_LINE;
    }

    /**
     * Returns a message String with help about the edit appointment command.
     *
     * @return String with help about the edit appointment command
     */
    public static String getEditAppointmentHelpMessage() {
        return "Edit an appointment’s information. MedBot will check if the edited appointment clashes with "
                + "others and display an error message if it does." + END_LINE
                + "Format: edit APPOINTMENT_ID [p/PATIENT_ID] [s/STAFF_ID] [d/DATE_TIME]" + END_LINE;
    }

    /**
     * Returns a message String with help about the find appointment command.
     *
     * @return String with help about the find appointment command
     */
    public static String getFindAppointmentHelpMessage() {
        return "Finds a person’s list of appointments. The list can be filtered by date-time to"
                + " display the list of appointments before/after a certain date." + END_LINE
                + "Format: find PERSON_TYPE/PERSON_ID [FILTER_TYPE/DATE_TIME]" + END_LINE
                + "The format for DATE_TIME is DDMMYY hhmm. I.e. 9 February 2021, "
                + "0800HRS should be written as 090221 0800" + END_LINE
                + "PERSON_TYPE is p (patient) or s (staff)" + END_LINE
                + "FILTER_TYPE is b (before) or a (after)" + END_LINE;
    }

    /**
     * Returns a message String with help about the view appointment command.
     *
     * @return String with help about the view appointment command
     */
    public static String getViewAppointmentHelpMessage() {
        return "View the information of an appointment." + END_LINE
                + "Format: view APPOINTMENT_ID" + END_LINE;
    }

    /**
     * Returns a message String with help about the list appointment command.
     *
     * @return String with help about the list appointment command
     */
    public static String getListAppointmentHelpMessage() {
        return "List the information of all appointments, including those of hidden patients." + END_LINE
                + "Format: list " + END_LINE;
    }

    /**
     * Returns a message String with help about the delete appointment command.
     *
     * @return String with help about the delete appointment command
     */
    public static String getDeleteAppointmentHelpMessage() {
        return "Delete an appointment from the list." + END_LINE
                + "Format: delete APPOINTMENT_ID" + END_LINE;
    }

    /**
     * Returns a message String indicating that there are no appointments which match the given criteria.
     *
     * @return String indicating that there are no appointments which match the given criteria.
     */
    public static String getNoAppointmentsFoundMessage() {
        return "Sorry, no appointments meeting that criteria were found!";
    }

    /**
     * Returns a message String indicating that there are no appointments in the system.
     *
     * @return String indicating that there are no appointments in the system.
     */
    public static String getNoAppointmentsMessage() {
        return "Sorry, there are no appointments found.";
    }

    /**
     * Returns a String containing the information of appointments that match the given criteria.
     *
     * @return String containing the information of appointments that match the given criteria
     */
    public static String getFindAppointmentListMessage(String appointmentString) {
        return "Here is a list of matched appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString
                + TABLE_ROW_SEPARATOR;
    }

    public static String getViewAppointmentMessage(String appointmentInfo) {
        return "Here is the requested appointment information:" + END_LINE + END_LINE
                + appointmentInfo;
    }

    /**
     * Returns a String containing information of all appointments in a table.
     *
     * @return String containing information of all appointments in a table
     */
    public static String getListAppointmentListMessage(String appointmentString) {
        return "Here is a list of all appointments:" + END_LINE
                + TABLE_ROW_SEPARATOR
                + APPOINTMENT_TABLE_HEADER
                + TABLE_ROW_SEPARATOR
                + appointmentString
                + TABLE_ROW_SEPARATOR;
    }

    /**
     * Returns a message String indicating that the appointment with the specified information has been added
     * to the system.
     *
     * @param appointmentInfo String containing the information about the appointment
     * @return String indicating that the specified appointment has been added to the system
     */
    public static String getAddAppointmentMessage(String appointmentInfo) {
        return "Added appointment with " + appointmentInfo;
    }

    /**
     * Returns a message String indicating that the appointment with the specified ID has been deleted from the system.
     *
     * @param id ID of the appointment who was removed from the system
     * @return String indicating that the specified appointment has been deleted from the system
     */
    public static String getDeleteAppointmentMessage(int id) {
        return "Appointment with ID " + id + " deleted from system." + END_LINE;
    }

    /**
     * Returns a message String indicating that the appointment with the specified ID has had their information edited
     * to the specified information.
     *
     * @param id ID of the patient whose information was edited
     * @param appointmentInfo String containing the new information of the patient
     * @return String indicating that the specified patient's information has been edited
     */
    public static String getEditAppointmentMessage(int id, String appointmentInfo) {
        return "The information of appointment with ID " + id + " has been edited to:" + END_LINE + END_LINE
                + appointmentInfo + END_LINE;
    }

    /**
     * Returns a message String indicating the current view.
     *
     * @return String indicating the current view.
     */
    public static String getCurrentViewMessage() {
        return "You are currently in the Scheduler view.";
    }
}
