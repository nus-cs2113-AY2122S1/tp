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

    public void addAppointment(Appointment appointment) throws MedBotException {
        if (!appointment.isComplete()) {
            throw new MedBotException(ERROR_ADD_INCOMPLETE_APPOINTMENT);
        }
        int patientId = appointment.getPatientId();
        int medicalStaffId = appointment.getMedicalStaffId();
        int dateTimeCode = appointment.getDateTimeCode();
        checkPatientAvailability(patientId, dateTimeCode);
        checkMedicalStaffAvailability(medicalStaffId, dateTimeCode);
        try {
            int appointmentId = schedulerAppointmentList.addAppointment(appointment);
            appointment.setAppointmentId(appointmentId);
            patientList.getPersonalAppointmentList(patientId).addAppointment(appointment);
            medicalStaffList.getPersonalAppointmentList(medicalStaffId).addAppointment(appointment);
        } catch (MedBotException me) {
            throw new MedBotException(ERROR_ADD_APPOINTMENT_ERROR);
        }
    }

    public void deleteAppointment(int appointmentId) throws MedBotException {
        Appointment deletedAppointment = schedulerAppointmentList.deleteAppointment(appointmentId);
        int patientId = deletedAppointment.getPatientId();
        int medicalStaffId = deletedAppointment.getMedicalStaffId();
        int dateTimeCode = deletedAppointment.getDateTimeCode();
        try {
            patientList.getPersonalAppointmentList(patientId).deleteAppointmentByTime(dateTimeCode);
            medicalStaffList.getPersonalAppointmentList(medicalStaffId).deleteAppointmentByTime(dateTimeCode);
        } catch (MedBotException me) {
            throw new MedBotException(ERROR_DELETE_APPOINTMENT_ERROR);
        }
    }

    public Appointment editAppointment(int appointmentId, Appointment newAppointment) throws MedBotException {
        Appointment oldAppointment = schedulerAppointmentList.getAppointment(appointmentId);

        newAppointment = Appointment.mergeAppointmentData(oldAppointment, newAppointment);
        newAppointment.setAppointmentId(appointmentId);

        int newPatientId = newAppointment.getPatientId();
        int newStaffId = newAppointment.getMedicalStaffId();
        int newDateTimeCode = newAppointment.getDateTimeCode();
        int patientAvail = patientList.getPersonalAppointmentList(newPatientId).getAppointmentId(newDateTimeCode);
        if (patientAvail != -1 && patientAvail != appointmentId) {
            throw new MedBotException(String.format(ERROR_PATIENT_APPOINTMENT_CLASH, patientAvail));
        }
        int staffAvail = medicalStaffList.getPersonalAppointmentList(newStaffId).getAppointmentId(newDateTimeCode);
        if (staffAvail != -1 && staffAvail != appointmentId) {
            throw new MedBotException(String.format(ERROR_STAFF_APPOINTMENT_CLASH, staffAvail));
        }
        deleteAppointment(appointmentId);
        schedulerAppointmentList.addAppointment(newAppointment);
        patientList.getPersonalAppointmentList(newPatientId).addAppointment(newAppointment);
        medicalStaffList.getPersonalAppointmentList(newStaffId).addAppointment(newAppointment);
        return newAppointment;
    }


    private void checkPatientAvailability(int patientId, int dateTimeCode) throws MedBotException {
        int clashAppointmentId = patientList.getPersonalAppointmentList(patientId).getAppointmentId(dateTimeCode);
        if (clashAppointmentId != -1) {
            throw new MedBotException(String.format(ERROR_PATIENT_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }

    private void checkMedicalStaffAvailability(int patientId, int dateTimeCode) throws MedBotException {
        int clashAppointmentId = medicalStaffList.getPersonalAppointmentList(patientId).getAppointmentId(dateTimeCode);
        if (clashAppointmentId != -1) {
            throw new MedBotException(String.format(ERROR_STAFF_APPOINTMENT_CLASH, clashAppointmentId));
        }
    }


}
