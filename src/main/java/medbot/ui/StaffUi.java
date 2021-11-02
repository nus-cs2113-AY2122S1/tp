package medbot.ui;

public class StaffUi extends PersonUi {
    private static final String STAFF = "staff";

    public static String getStaffCommandList() {
        return getCommandList();
    }

    public static String getAddStaffHelpMessage() {
        return getAddHelpMessage(STAFF);
    }

    public static String getEditStaffHelpMessage() {
        return getEditHelpMessage(STAFF);
    }

    public static String getFindStaffHelpMessage() {
        return getFindHelpMessage(STAFF);
    }

    public static String getViewStaffHelpMessage() {
        return getViewHelpMessage(STAFF);
    }

    public static String getListStaffHelpMessage() {
        return getListHelpMessage(STAFF);
    }

    public static String getDeleteStaffHelpMessage() {
        return getDeleteHelpMessage(STAFF);
    }

    public static String getArchiveStaffHelpMessage() {
        return getArchiveHelpMessage(STAFF);
    }

    public static String getUnarchiveStaffHelpMessage() {
        return getUnarchiveHelpMessage(STAFF);
    }

    public static String getAddStaffMessage(String staffInfo) {
        return getAddPersonMessage(STAFF, staffInfo);
    }

    public static String getDeleteStaffMessage(int id) {
        return getDeletePersonMessage(STAFF, id);
    }

    public static String getEditStaffMessage(int id, String info) {
        return getEditPersonMessage(STAFF, id, info);
    }

    public static String getArchiveStaffMessage(int id) {
        return getArchivePersonMessage(STAFF, id);
    }

    public static String getUnarchiveStaffMessage(int id) {
        return getUnarchivePersonMessage(STAFF, id);
    }

    public static String getCurrentViewStaffMessage() {
        return getCurrentViewPersonMessage(STAFF);
    }

    /**
     * Returns the profile of a staff.
     *
     * @param staffInfo the Info of the patient to be printed.
     * @return the staff information
     */
    public static String getStaffInfo(String staffInfo) {
        return getPersonInfo(STAFF, staffInfo);
    }

    /**
     * Returns all staffs' information in a list.
     *
     * @param staffListString String containing information of all staff.
     * @return all Staffs' information.
     */
    public static String getAllStaffsString(String staffListString) {
        return getAllPersonsString(STAFF, staffListString);
    }

    /**
     * Returns the header of the staff information table.
     *
     * @return the header of the staff information table.
     */
    public static String getStaffTableHeader() {
        return getTableHeader(STAFF);
    }
}
