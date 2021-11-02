package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.list.SchedulerAppointmentList;
import medbot.person.Person;
import medbot.utilities.FilterType;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private static final String EMPTY_STRING = "";
    private static final String END_LINE = System.lineSeparator();
    private static final String SPACE = " ";
    private static final String VERTICAL_LINE_SPACED = " | ";

    private static final int LENGTH_APPOINTMENT_ID_COLUMN = 4;
    private static final int LENGTH_PATIENT_ID_COLUMN = 10;
    private static final int LENGTH_STAFF_ID_COLUMN = 8;
    private static final int LENGTH_NAME_COLUMN = 20;

    private static final String ERROR_ADD_INCOMPLETE_APPOINTMENT = "Incomplete appointment.";
    private static final String ERROR_PATIENT_APPOINTMENT_CLASH = "Patient unavailable, appointment %d at that time.";
    private static final String ERROR_STAFF_APPOINTMENT_CLASH = "Staff unavailable, appointment %d at that time.";

    private static final String ERROR_ADD_APPOINTMENT_ERROR = "Add appointment error.";
    private static final String ERROR_DELETE_APPOINTMENT_ERROR = "Delete appointment error.";

    private final PatientList patientList = new PatientList();
    private final MedicalStaffList medicalStaffList = new MedicalStaffList();
    private final SchedulerAppointmentList schedulerAppointmentList = new SchedulerAppointmentList();

    //Patient and Staff Management methods

    /**
     * Adds the given patient into the scheduler, allocates an id to the patient and returns the id value.
     *
     * @param patient Patient to be added into the scheduler
     * @return personId that was allocated to the patient
     */
    public int addPatient(Person patient) {
        return patientList.addPerson(patient);
    }

    /**
     * Adds the given staff into the scheduler, allocates an id to the staff and returns the id value.
     *
     * @param staff Staff to be added into the scheduler
     * @return personId that was allocated to the staff
     */
    public int addStaff(Person staff) {
        return medicalStaffList.addPerson(staff);
    }

    /**
     * Edits the specified fields on Patient information with new values from the user.
     *
     * @param patientId      the id of the Patient with information to change
     * @param newPatientData the new Patient data to change to (except the null fields)
     * @throws MedBotException if there is no patient with that id
     */
    public void editPatient(int patientId, Person newPatientData) throws MedBotException {
        patientList.editPerson(patientId, newPatientData);
    }

    /**
     * Edits the specified fields on Staff information with new values from the user.
     *
     * @param staffId      the id of the Staff with information to change
     * @param newStaffData the new Staff data to change to (except the null fields)
     * @throws MedBotException if there is no staff with that id
     */
    public void editStaff(int staffId, Person newStaffData) throws MedBotException {
        medicalStaffList.editPerson(staffId, newStaffData);
    }

    /**
     * Removes the patient with the specified id.
     *
     * @param patientId the id of the patient to remove.
     * @throws MedBotException if there is no patient with that id
     */
    public void deletePatient(int patientId) throws MedBotException {
        LinkedList<Integer> appointmentIds = patientList.getAllAppointmentIds(patientId);
        deleteAppointments(appointmentIds);
        patientList.deletePerson(patientId);
    }

    /**
     * Removes the staff with the specified id.
     *
     * @param staffId the id of the staff to remove.
     * @throws MedBotException if there is no staff with that id
     */
    public void deleteStaff(int staffId) throws MedBotException {
        LinkedList<Integer> appointmentIds = medicalStaffList.getAllAppointmentIds(staffId);
        deleteAppointments(appointmentIds);
        medicalStaffList.deletePerson(staffId);
    }

    /**
     * Delete all appointments whose id is in the given appointmentIds List.
     *
     * @param appointmentIds List of ids of appointments to be deleted
     * @throws MedBotException if there is an error when deleting the appointments
     */
    private void deleteAppointments(List<Integer> appointmentIds) throws MedBotException {
        for (int appointmentId : appointmentIds) {
            try {
                deleteAppointment(appointmentId);
            } catch (MedBotException mbe) {
                //This exception should not be thrown
                assert false;
                throw new MedBotException(ERROR_DELETE_APPOINTMENT_ERROR);
            }
        }
    }

    /**
     * Returns a String containing the information of the patient with the specified patientId.
     *
     * @param patientId the id of the patient to search for
     * @return a String containing the patient's information
     * @throws MedBotException if there is no patient with that id
     */
    public String getPatientInfo(int patientId) throws MedBotException {
        return patientList.getPersonInfo(patientId);
    }

    /**
     * Returns a String containing the information of the staff with the specified patientId.
     *
     * @param staffId the id of the staff to search for
     * @return a String containing the staff's information
     * @throws MedBotException if there is no staff with that id
     */
    public String getStaffInfo(int staffId) throws MedBotException {
        return medicalStaffList.getPersonInfo(staffId);
    }

    /**
     * Returns a list of patients that match the specified attributes.
     *
     * @param parameters The attributes to filter patients
     * @return list of patients that match the specified attributes
     */
    public List<String> findPatients(String[] parameters) throws MedBotException {
        return patientList.findPersons(parameters);
    }

    /**
     * Returns a list of staff that match the specified attributes.
     *
     * @param parameters The attributes to filter patients
     * @return list of staff that match the specified attributes
     */
    public List<String> findStaff(String[] parameters) throws MedBotException {
        return medicalStaffList.findPersons(parameters);
    }

    /**
     * Returns a String that containing information of all patients.
     *
     * @return String containing information of all patients.
     */
    public String listPatients(boolean getHiddenPatients) {
        return patientList.listPersons(getHiddenPatients);
    }

    /**
     * Returns a String that containing information of all staff.
     *
     * @return String containing information of all staff.
     */
    public String listStaff(boolean getHiddenStaffs) {
        return medicalStaffList.listPersons(getHiddenStaffs);
    }

    /**
     * Hides the patient with the specified id.
     *
     * @param patientId the id of the patient to hide.
     * @throws MedBotException if there is no patient with that id
     */
    public void hidePatient(int patientId) throws MedBotException {
        patientList.hidePerson(patientId);
    }

    /**
     * Hides the staff with the specified id.
     *
     * @param staffId the id of the staff to hide.
     * @throws MedBotException if there is no staff with that id
     */
    public void hideStaff(int staffId) throws MedBotException {
        medicalStaffList.hidePerson(staffId);
    }

    /**
     * Shows the patient with the specified id.
     *
     * @param patientId the id of the patient to show.
     * @throws MedBotException if there is no patient with that id
     */
    public void showPatient(int patientId) throws MedBotException {
        patientList.showPerson(patientId);
    }

    /**
     * Shows the staff with the specified id.
     *
     * @param staffId the id of the staff to show.
     * @throws MedBotException if there is no staff with that id
     */
    public void showStaff(int staffId) throws MedBotException {
        medicalStaffList.showPerson(staffId);
    }

    //Storage methods

    public int getLastPatientId() {
        return patientList.getLastId();
    }

    public void setLastPatientId(int lastPatientId) {
        patientList.setLastId(lastPatientId);
    }

    public String getPatientStorageString() {
        return patientList.getStorageString();
    }

    public int getLastStaffId() {
        return medicalStaffList.getLastId();
    }

    public void setLastStaffId(int lastStaffId) {
        medicalStaffList.setLastId(lastStaffId);
    }

    public String getStaffStorageString() {
        return medicalStaffList.getStorageString();
    }

    public int getLastAppointmentId() {
        return schedulerAppointmentList.getLastId();
    }

    public void setLastAppointmentId(int lastAppointmentId) {
        schedulerAppointmentList.setLastId(lastAppointmentId);
    }

    public String getAppointmentStorageString() {
        return schedulerAppointmentList.getStorageString();
    }

    //Appointment Management methods

    /**
     * Returns a copy of the appointment at the specified id.
     *
     * @param appointmentId the id of the Appointment to be returned
     * @return a copy of the appointment at the specified id
     * @throws MedBotException if there is no appointment with that id
     */
    public Appointment getAppointment(int appointmentId) throws MedBotException {
        return schedulerAppointmentList.getAppointment(appointmentId);
    }

    /**
     * Returns all current appointments.
     *
     * @return the String list of all appointments.
     * @throws MedBotException when the appointment List cannot be generated.
     */
    public String listAllAppointments() throws MedBotException {
        List<Integer> appointmentIds = schedulerAppointmentList.listAppointments();
        return generateAppointmentTable(appointmentIds);
    }

    /**
     * Returns all current appointments of a staff.
     *
     * @return the String list of all appointments of a staff.
     * @throws MedBotException when the appointment List cannot be generated.
     */
    public String listMedicalStaffAppointments(int staffId, FilterType filterType, int dateTimeCode)
            throws MedBotException {
        List<Integer> appointmentIds = medicalStaffList.listAppointments(staffId, filterType, dateTimeCode);
        return generateAppointmentTable(appointmentIds);
    }

    /**
     * Returns all current appointments of a patient.
     *
     * @return the String list of all appointments of a patient.
     * @throws MedBotException when the appointment List cannot be generated.
     */
    public String listPatientAppointments(int patientId, FilterType filterType, int dateTimeCode)
            throws MedBotException {
        List<Integer> appointmentIds = patientList.listAppointments(patientId, filterType, dateTimeCode);
        return generateAppointmentTable(appointmentIds);
    }

    /**
     * Adds the specified appointment to the scheduler if it does not clash with existing appointments.
     *
     * <p>If the appointment does not have an appointmentId, it will be assigned one.
     *
     * @param appointment Appointment to be added to the scheduler
     * @throws MedBotException if the appointment is missing information or clashes with another appointment
     */
    public int addAppointment(Appointment appointment) throws MedBotException {
        if (!appointment.isComplete()) {
            throw new MedBotException(ERROR_ADD_INCOMPLETE_APPOINTMENT);
        }
        checkAvailability(appointment);
        return insertAppointment(appointment);
    }

    /**
     * Removes the appointment with the specified appointmentId.
     *
     * @param appointmentId the appointmentId of the appointment to be removed
     * @throws MedBotException if there is no appointment with that appointmentId
     */
    public void deleteAppointment(int appointmentId) throws MedBotException {
        Appointment deletedAppointment = schedulerAppointmentList.deleteAppointment(appointmentId);
        int patientId = deletedAppointment.getPatientId();
        int medicalStaffId = deletedAppointment.getMedicalStaffId();
        int dateTimeCode = deletedAppointment.getDateTimeCode();
        try {
            patientList.deleteAppointment(patientId, dateTimeCode);
            medicalStaffList.deleteAppointment(medicalStaffId, dateTimeCode);
        } catch (MedBotException me) {
            //This exception should not be thrown as the patientId, medicalStaffId and dateTimeCode should correspond
            //to a valid appointment
            assert false;
            throw new MedBotException(ERROR_DELETE_APPOINTMENT_ERROR);
        }
    }

    /**
     * Edits the appointment at the specified appointmentId to have the parameters of newAppointment.
     *
     * @param appointmentId  the id of the appointment to be modified
     * @param newAppointment Appointment containing the new parameter values
     * @throws MedBotException if there is no appointment with the specified id or the changes will lead to a clash
     */
    public void editAppointment(int appointmentId, Appointment newAppointment) throws MedBotException {
        Appointment oldAppointment = schedulerAppointmentList.getAppointment(appointmentId);
        newAppointment = Appointment.mergeAppointmentData(oldAppointment, newAppointment);
        newAppointment.setId(appointmentId);
        assert newAppointment.isComplete();

        checkAvailability(newAppointment);
        deleteAppointment(appointmentId);
        insertAppointment(newAppointment);
    }

    private int insertAppointment(Appointment appointment) throws MedBotException {
        try {
            int appointmentId = schedulerAppointmentList.addAppointment(appointment);
            patientList.addAppointment(appointment.getPatientId(), appointment);
            medicalStaffList.addAppointment(appointment.getMedicalStaffId(), appointment);
            return appointmentId;
        } catch (MedBotException me) {
            //This exception should not be thrown as it was already checked that there would be no clashes when
            //inserting the appointment
            assert false;
            throw new MedBotException(ERROR_ADD_APPOINTMENT_ERROR);
        }
    }

    private void checkAvailability(Appointment appointment) throws MedBotException {
        int patientId = appointment.getPatientId();
        int staffId = appointment.getMedicalStaffId();
        int dateTimeCode = appointment.getDateTimeCode();
        int appointmentId = appointment.getId();
        checkPatientAvailability(patientId, dateTimeCode, appointmentId);
        checkStaffAvailability(staffId, dateTimeCode, appointmentId);
    }

    private void checkPatientAvailability(int patientId, int dateTimeCode, int appointmentId) throws MedBotException {
        int clashAppointmentId = patientList.getAppointmentId(patientId, dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_PATIENT_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }

    private void checkStaffAvailability(int staffId, int dateTimeCode, int appointmentId)
            throws MedBotException {
        int clashAppointmentId = medicalStaffList.getAppointmentId(staffId, dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_STAFF_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }

    private String generateAppointmentTable(List<Integer> appointmentIds) throws MedBotException {
        String output = EMPTY_STRING;
        for (int appointmentId : appointmentIds) {
            output += generateAppointmentTableRow(appointmentId) + END_LINE;
        }
        return output;
    }

    private String generateAppointmentTableRow(int appointmentId) throws MedBotException {
        Appointment appointment = schedulerAppointmentList.getAppointment(appointmentId);
        String patientName = patientList.getPersonName(appointment.getPatientId());
        String staffName = medicalStaffList.getPersonName(appointment.getMedicalStaffId());
        return VERTICAL_LINE_SPACED + formatAppointmentId(appointment.getId())
                + VERTICAL_LINE_SPACED + appointment.getDateTimeString()
                + VERTICAL_LINE_SPACED + formatPatientId(appointment.getPatientId())
                + VERTICAL_LINE_SPACED + formatNameString(patientName)
                + VERTICAL_LINE_SPACED + formatStaffId(appointment.getMedicalStaffId())
                + VERTICAL_LINE_SPACED + formatNameString(staffName)
                + VERTICAL_LINE_SPACED;
    }

    private String formatAppointmentId(int id) {
        String idString = Integer.toString(id);
        return formatAttributeString(idString, LENGTH_APPOINTMENT_ID_COLUMN);
    }

    private String formatPatientId(int id) {
        String idString = Integer.toString(id);
        return formatAttributeString(idString, LENGTH_PATIENT_ID_COLUMN);
    }

    private String formatStaffId(int id) {
        String idString = Integer.toString(id);
        return formatAttributeString(idString, LENGTH_STAFF_ID_COLUMN);
    }

    private String formatNameString(String nameString) {
        return formatAttributeString(nameString, LENGTH_NAME_COLUMN);
    }

    private String formatAttributeString(String attribute, int outputLength) {
        int attributeLength = attribute.length();
        String output = attribute;

        if (attributeLength > outputLength) {
            output = output.substring(0, outputLength - 3) + "...";
            return output;
        }

        for (int i = attributeLength; i < outputLength; i++) {
            output += SPACE;
        }
        return output;
    }
}
