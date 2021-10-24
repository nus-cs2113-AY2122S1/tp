package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.list.SchedulerAppointmentList;

public class Scheduler {
    private static final String ERROR_ADD_INCOMPLETE_APPOINTMENT = "Incomplete appointment.";
    private static final String ERROR_PATIENT_APPOINTMENT_CLASH = "Patient unavailable, appointment %d at that time.";
    private static final String ERROR_STAFF_APPOINTMENT_CLASH = "Staff unavailable, appointment %d at that time.";

    private static final String ERROR_ADD_APPOINTMENT_ERROR = "Add appointment error.";
    private static final String ERROR_EDIT_APPOINTMENT_ERROR = "Edit appointment error.";
    private static final String ERROR_DELETE_APPOINTMENT_ERROR = "Delete appointment error.";

    private final PatientList patientList = new PatientList();
    private final MedicalStaffList medicalStaffList = new MedicalStaffList();
    private final SchedulerAppointmentList schedulerAppointmentList = new SchedulerAppointmentList();

    public PatientList getPatientList() {
        return patientList;
    }

    public MedicalStaffList getMedicalStaffList() {
        return medicalStaffList;
    }

    public String listAllAppointments() {
        return schedulerAppointmentList.listAppointments();
    }

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
     * Adds the specified appointment to the scheduler if it does not clash with existing appointments.
     *
     * <p>If the appointment does not have an appointmentId, it will be assigned one.
     *
     * @param appointment Appointment to be added to the scheduler
     * @return the appointmentId of the appointment after it was added to the scheduler
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
            //No exception should be thrown
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
        newAppointment.setAppointmentId(appointmentId);
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
            //No exception should be thrown
            assert false;
            throw new MedBotException(ERROR_ADD_APPOINTMENT_ERROR);
        }
    }

    private void checkAvailability(Appointment appointment) throws MedBotException {
        int patientId = appointment.getPatientId();
        int staffId = appointment.getMedicalStaffId();
        int dateTimeCode = appointment.getDateTimeCode();
        int appointmentId = appointment.getAppointmentId();
        checkPatientAvailability(patientId, dateTimeCode, appointmentId);
        checkMedicalStaffAvailability(staffId, dateTimeCode, appointmentId);
    }

    private void checkPatientAvailability(int patientId, int dateTimeCode, int appointmentId) throws MedBotException {
        int clashAppointmentId = patientList.getAppointmentId(patientId, dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_PATIENT_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }

    private void checkMedicalStaffAvailability(int staffId, int dateTimeCode, int appointmentId)
            throws MedBotException {
        int clashAppointmentId = medicalStaffList.getAppointmentId(staffId, dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_STAFF_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }
}
