package medbot.ui;

public class PatientUi extends PersonUi {
    private static final String PATIENT = "patient";

    /**
     * Returns a list of valid commands.
     *
     * @return String containing a list of valid commands
     */
    public static String getPatientCommandList() {
        return getCommandList();
    }

    /**
     * Returns a message String with help about the add patient command.
     *
     * @return String with help about the add patient command
     */
    public static String getAddPatientHelpMessage() {
        return getAddHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the delete patient command.
     *
     * @return String with help about the delete patient command
     */
    public static String getDeletePatientHelpMessage() {
        return getDeleteHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the edit patient command.
     *
     * @return String with help about the edit patient command
     */
    public static String getEditPatientHelpMessage() {
        return getEditHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the view patient command.
     *
     * @return String with help about the view patient command
     */
    public static String getViewPatientHelpMessage() {
        return getViewHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the list patient command.
     *
     * @return String with help about the list patient command
     */
    public static String getListPatientHelpMessage() {
        return getListHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the find patient command.
     *
     * @return String with help about the find patient command
     */
    public static String getFindPatientHelpMessage() {
        return getFindHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the hide command.
     *
     * @return String with help about the hide command
     */
    public static String getHidePatientHelpMessage() {
        return getHideHelpMessage(PATIENT);
    }

    /**
     * Returns a message String with help about the show command.
     *
     * @return String with help about the show command
     */
    public static String getShowPatientHelpMessage() {
        return getShowHelpMessage(PATIENT);
    }

    /**
     * Returns a message String indicating that the patient with the specified information has been added
     * to the system.
     *
     * @param patientInfo String containing the information about the patient
     * @return String indicating that the specified patient has been added to the system
     */
    public static String getAddPatientMessage(String patientInfo) {
        return getAddPersonMessage(PATIENT, patientInfo);
    }

    /**
     * Returns a message String indicating that the patient with the specified ID has been deleted from the system.
     *
     * @param id ID of the patient who was removed from the system
     * @return String indicating that the specified patient has been deleted from the system
     */
    public static String getDeletePatientMessage(int id) {
        return getDeletePersonMessage(PATIENT, id);
    }

    /**
     * Returns a message String indicating that the patient with the specified ID has had their information edited
     * to the specified information.
     *
     * @param id ID of the patient whose information was edited
     * @param patientInfo String containing the new information of the patient
     * @return String indicating that the specified patient's information has been edited
     */
    public static String getEditPatientMessage(int id, String patientInfo) {
        return getEditPersonMessage(PATIENT, id, patientInfo);
    }

    /**
     * Returns a message String indicating that the patient with the specified ID is now set to hidden.
     *
     * @param id ID of the patient who was set to shown
     * @return String indicating that the patient with the specified ID is now set to hidden
     */
    public static String getHidePatientMessage(int id) {
        return getHidePersonMessage(PATIENT, id);
    }

    /**
     * Returns a message String indicating that the patient with the specified ID is now set to shown.
     *
     * @param id ID of the patient who was set to shown
     * @return String indicating that the patient with the specified ID is now shown
     */
    public static String getShowPatientMessage(int id) {
        return getShowPersonMessage(PATIENT, id);
    }

    /**
     * Returns a message String indicating the current view.
     *
     * @return String indicating the current view.
     */
    public static String getCurrentViewPatientMessage() {
        return getCurrentViewPersonMessage(PATIENT);
    }

    /**
     * Returns a message String containing the information of the patient.
     *
     * @param patientInfo the info of the patient to be printed.
     * @return String containing the information of the patient
     */
    public static String getViewPatientMessage(String patientInfo) {
        return getViewPersonMessage(PATIENT, patientInfo);
    }

    /**
     * Returns a String containing all patient information in a table.
     *
     * @param patientListString String containing information of all patients
     * @return String containing all patient information in a table
     */
    public static String getAllPatientsString(String patientListString, boolean isHiddenPersonList) {
        return getAllPersonsString(PATIENT, patientListString, isHiddenPersonList);
    }

    /**
     * Returns a String containing the header of the patient information table for matched patients.
     *
     * @return String containing the header of the patient information table for matched patients.
     */
    public static String getPatientTableMatchedHeader() {
        return getTableMatchedHeader(PATIENT);
    }

}
