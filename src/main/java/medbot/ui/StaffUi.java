package medbot.ui;

import medbot.list.PersonList;

public class StaffUi {
    public static final String END_LINE = System.lineSeparator();
    public static final String TABLE_ROW_SEPARATOR = " ------------------------------------------------"
            + "----------------------------------------------------- " + END_LINE;

    public static String getAddStaffHelpMessage() {
        return " " + END_LINE;
    }

    public static String getEditStaffHelpMessage() {
        return " " + END_LINE;
    }

    public static String getFindStaffHelpMessage() {
        return " " + END_LINE;
    }

    public static String getViewStaffHelpMessage() {
        return " " + END_LINE;
    }

    public static String getListStaffHelpMessage() {
        return " " + END_LINE;
    }

    public static String getDeleteStaffHelpMessage() {
        return " " + END_LINE;
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
     * @param staffList the list containing staffs to be printed.
     * @return all Staffs' information.
     */
    public static String getAllStaffsString(PersonList staffList) {
        String output = getStaffTableHeader();
        output += staffList.listPersons();
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
