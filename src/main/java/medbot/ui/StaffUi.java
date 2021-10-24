package medbot.ui;

public class StaffUi {
    public static final String END_LINE = System.lineSeparator();
    public static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    public static String getAddStaffHelpMessage() {
        return "Add a staff to the staff’s list." + END_LINE
                + "Format:" + END_LINE
                + "add i/STAFF_IC [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output:" + END_LINE
                + "Added staff with staff ID: STAFF_ID" + END_LINE;
    }

    public static String getEditStaffHelpMessage() {
        return "Edit the personal information of a staff in the list." + END_LINE
                + "Format:" + END_LINE
                + "edit STAFF ID [i/STAFF_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "Expected output: " + END_LINE
                + "The information of staff with ID STAFF_ID has been edited to:" + END_LINE
                + "Staff ID: [STAFF_ID] IC: [STAFF_IC] Name: [NAME] H/P: [PHONE_NUMBER] "
                + "Email: [EMAIL] Address: [ADDRESS] " + END_LINE;
    }

    public static String getFindStaffHelpMessage() {
        return "Find staffs from the list based on given attributes." + END_LINE
                + "Format:" + END_LINE
                + "find [i/STAFF_IC] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]" + END_LINE
                + "    * The attributes do not have to be in full." + END_LINE
                + "    * At least one attribute must be present." + END_LINE
                + "Expected Output:" + END_LINE
                + "Staff ID: STAFF_ID IC: STAFF_IC Name: NAME "
                + "H/P: PHONE_NUMBER Email: EMAIL Address: ADDRESS" + END_LINE;
    }

    public static String getViewStaffHelpMessage() {
        return "View a staff’s personal information." + END_LINE
                + "Format: view STAFF_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "id: STAFF_ID " + "name: NAME " + "phone number: PHONE_NUMBER "
                + "email: EMAIL " + "address: ADDRESS" + END_LINE;
    }

    public static String getListStaffHelpMessage() {
        return "View information of all current staffs." + END_LINE
                + "Format: list" + END_LINE
                + "Expected Output for 2 staffs: " + END_LINE
                + "Staff ID: [STAFF_ID_1] IC: [STAFF_IC]"
                + "Name: [STAFF_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE
                + "Staff ID: [STAFF_ID_2] IC: [STAFF_IC]"
                + "Name: [STAFF_NAME] H/P: [PHONE NUMBER] Email: [EMAIL]  Address: [ADDRESS]" + END_LINE;
    }

    public static String getDeleteStaffHelpMessage() {
        return "Delete a staff from the list." + END_LINE
                + "Format:" + END_LINE
                + "delete STAFF_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "Staff with id STAFF_ID deleted from system." + END_LINE;
    }

    public static String getArchiveStaffHelpMessage() {
        return "Archive a staff from the list." + END_LINE
                + "Format:" + END_LINE
                + "archive STAFF_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "Staff with id STAFF_ID is successfully archived." + END_LINE;
    }

    public static String getUnarchiveStaffHelpMessage() {
        return "Unarchive a staff from the list." + END_LINE
                + "Format:" + END_LINE
                + "unarchive STAFF_ID" + END_LINE
                + "Expected Output:" + END_LINE
                + "Staff with id STAFF_ID is successfully unarchived." + END_LINE;
    }

    /**
     * Prints a message when viewing the profile of a staff.
     *
     * @param staffInfo the Info of the patient to be printed.
     * @return the staff information
     */
    public static String getStaffInfo(String staffInfo) {
        return "Here's the requested staff:" + END_LINE + END_LINE
                + staffInfo + END_LINE;
    }

    /**
     * Prints all staffs in a list.
     *
     * @param staffListString String containing information of all staff.
     * @return all Staffs' information.
     */
    public static String getAllStaffsString(String staffListString) {
        String output = getStaffTableHeader();
        output += staffListString;
        output += TABLE_ROW_SEPARATOR;

        return output;
    }

    public static String getStaffTableHeader() {
        String output = "Here is a list of all staffs:" + END_LINE;
        output += "For full details of each staff, please use the command \"view STAFF_ID\"" + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        output += " |  ID  | IC Number |         Name         |"
                + " Phone No. |        Email         |       Address        | " + END_LINE;
        output += TABLE_ROW_SEPARATOR;
        return output;
    }
}
