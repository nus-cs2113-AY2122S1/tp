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

    public SchedulerAppointmentList getSchedulerAppointmentList() {
        return schedulerAppointmentList;
    }

    public int addAppointment(Appointment appointment) throws MedBotException {
        if (!appointment.isComplete()) {
            throw new MedBotException(ERROR_ADD_INCOMPLETE_APPOINTMENT);
        }
        checkAvailability(appointment);
        return insertAppointment(appointment);
    }

    public void deleteAppointment(int appointmentId) throws MedBotException {
        Appointment deletedAppointment = schedulerAppointmentList.deleteAppointment(appointmentId);
        int patientId = deletedAppointment.getPatientId();
        int medicalStaffId = deletedAppointment.getMedicalStaffId();
        int dateTimeCode = deletedAppointment.getDateTimeCode();
        try {
            patientList.getPersonalAppointmentList(patientId).deleteAppointment(dateTimeCode);
            medicalStaffList.getPersonalAppointmentList(medicalStaffId).deleteAppointment(dateTimeCode);
        } catch (MedBotException me) {
            throw new MedBotException(ERROR_DELETE_APPOINTMENT_ERROR);
        }
    }

    public Appointment editAppointment(int appointmentId, Appointment newAppointment) throws MedBotException {
        Appointment oldAppointment = schedulerAppointmentList.getAppointment(appointmentId);

        newAppointment = Appointment.mergeAppointmentData(oldAppointment, newAppointment);
        newAppointment.setAppointmentId(appointmentId);
        assert newAppointment.isComplete();

        checkAvailability(newAppointment);
        deleteAppointment(appointmentId);
        insertAppointment(newAppointment);
        return newAppointment;
    }

    private int insertAppointment(Appointment appointment) throws MedBotException {
        try {
            int appointmentId = schedulerAppointmentList.addAppointment(appointment);
            patientList.getPersonalAppointmentList(appointment.getPatientId()).addAppointment(appointment);
            medicalStaffList.getPersonalAppointmentList(appointment.getMedicalStaffId()).addAppointment(appointment);
            return appointmentId;
        } catch (MedBotException me) {
            assert false;
            throw new MedBotException(ERROR_ADD_APPOINTMENT_ERROR);
        }
    }

    private void checkAvailability(Appointment appointment) throws MedBotException {
        int patientId = appointment.getPatientId();
        int staffId = appointment.getMedicalStaffId();
        int dateTimeCode = appointment.getDateTimeCode();
        int appointmentId = appointment.getAppointmentId();
        checkPatientAvailability(patientId,dateTimeCode,appointmentId);
        checkMedicalStaffAvailability(staffId,dateTimeCode,appointmentId);
    }

    private void checkPatientAvailability(int patientId, int dateTimeCode, int appointmentId) throws MedBotException {
        int clashAppointmentId = patientList.getPersonalAppointmentList(patientId).getAppointmentId(dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_PATIENT_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }

    private void checkMedicalStaffAvailability(int staffId, int dateTimeCode, int appointmentId)
            throws MedBotException {
        int clashAppointmentId = medicalStaffList.getPersonalAppointmentList(staffId).getAppointmentId(dateTimeCode);
        if (clashAppointmentId != -1 && clashAppointmentId != appointmentId) {
            throw new MedBotException(String.format(ERROR_STAFF_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }
}
