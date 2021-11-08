package medbot.ui;

public class StaffUi extends PersonUi {
    private static final String STAFF = "staff";

    /**
     * Returns a list of valid commands.
     *
     * @return String containing a list of valid commands
     */
    public static String getStaffCommandList() {
        return getCommandList();
    }

    /**
     * Returns a message String with help about the list command.
     *
     * @return String with help about the list command
     */
    public static String getAddStaffHelpMessage() {
        return getAddHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the delete command.
     *
     * @return String with help about the delete command
     */
    public static String getDeleteStaffHelpMessage() {
        return getDeleteHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the edit command.
     *
     * @return String with help about the edit command
     */
    public static String getEditStaffHelpMessage() {
        return getEditHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the view command.
     *
     * @return String with help about the view command
     */
    public static String getViewStaffHelpMessage() {
        return getViewHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the view command.
     *
     * @return String with help about the view command
     */
    public static String getListStaffHelpMessage() {
        return getListHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the find command.
     *
     * @return String with help about the find command
     */
    public static String getFindStaffHelpMessage() {
        return getFindHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the hide command.
     *
     * @return String with help about the hide command
     */
    public static String getHideStaffHelpMessage() {
        return getHideHelpMessage(STAFF);
    }

    /**
     * Returns a message String with help about the show command.
     *
     * @return String with help about the show command
     */
    public static String getShowStaffHelpMessage() {
        return getShowHelpMessage(STAFF);
    }

    /**
     * Returns a message String indicating that the staff with the specified information has been added
     * to the system.
     *
     * @param staffInfo String containing the information about the staff
     * @return String indicating that the specified staff has been added to the system
     */
    public static String getAddStaffMessage(String staffInfo) {
        return getAddPersonMessage(STAFF, staffInfo);
    }

    /**
     * Returns a message String indicating that the staff with the specified ID has been deleted from the system.
     *
     * @param id ID of the staff who was removed from the system
     * @return String indicating that the specified staff has been deleted from the system
     */
    public static String getDeleteStaffMessage(int id) {
        return getDeletePersonMessage(STAFF, id);
    }

    /**
     * Returns a message String indicating that the staff with the specified ID has had their information edited
     * to the specified information.
     *
     * @param id ID of the staff whose information was edited
     * @param staffInfo String containing the new information of the staff
     * @return String indicating that the specified staff's information has been edited
     */
    public static String getEditStaffMessage(int id, String staffInfo) {
        return getEditPersonMessage(STAFF, id, staffInfo);
    }

    /**
     * Returns a message String indicating that the staff with the specified ID is now set to hidden.
     *
     * @param id ID of the staff who was set to shown
     * @return String indicating that the staff with the specified ID is now set to hidden
     */
    public static String getHideStaffMessage(int id) {
        return getHidePersonMessage(STAFF, id);
    }

    /**
     * Returns a message String indicating that the staff with the specified ID is now set to shown.
     *
     * @param id ID of the staff who was set to shown
     * @return String indicating that the staff with the specified ID is now shown
     */
    public static String getShowStaffMessage(int id) {
        return getShowPersonMessage(STAFF, id);
    }

    /**
     * Returns a message String indicating the current view.
     *
     * @return String indicating the current view.
     */
    public static String getCurrentViewStaffMessage() {
        return getCurrentViewPersonMessage(STAFF);
    }

    /**
     * Returns a message String containing the information of the staff.
     *
     * @param staffInfo the info of the staff to be printed.
     * @return String containing the information of the staff
     */
    public static String getViewStaffMessage(String staffInfo) {
        return getViewPersonMessage(STAFF, staffInfo);
    }

    /**
     * Returns a String containing all staff information in a table.
     *
     * @param staffListString String containing information of all staff.
     * @return String containing all staff information in a table
     */
    public static String getAllStaffsString(String staffListString, boolean isHiddenPersonList) {
        return getAllPersonsString(STAFF, staffListString, isHiddenPersonList);
    }

    /**
     * Returns a String containing the header of the staff information table for matched staff.
     *
     * @return String containing the header of the staff information table for matched staff.
     */
    public static String getStaffTableMatchedHeader() {
        return getTableMatchedHeader(STAFF);
    }
}
