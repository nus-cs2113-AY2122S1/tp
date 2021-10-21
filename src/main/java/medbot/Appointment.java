package medbot;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private static final ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH00");
    private int appointmentId = 0;
    private int patientId = 0;
    private int medicalStaffId = 0;
    private int dateTimeCode = 0;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public int getDateTimeCode() {
        return dateTimeCode;
    }

    public void setDateTimeCode(int dateTimeCode) {
        this.dateTimeCode = dateTimeCode;
    }

    public static String getDateTimeString(int dateTimeCode) {
        long epochSecond = (long) dateTimeCode * 60;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecond, 0, zoneOffset);
        return localDateTime.format(dateTimeFormatter);
    }

    public boolean isComplete() {
        if (patientId > 0 && medicalStaffId > 0 && dateTimeCode > 0) {
            return true;
        }
        return false;
    }

    public void mergeAppointmentData(Appointment newAppointment) {
        if (newAppointment.getDateTimeCode() != 0) {
            assert newAppointment.getDateTimeCode() > 0;
            setDateTimeCode(newAppointment.getDateTimeCode());
        }
        if (newAppointment.getPatientId() != 0) {
            assert newAppointment.getPatientId() > 0;
            setPatientId(newAppointment.getPatientId());
        }
        if (newAppointment.getMedicalStaffId() != 0) {
            assert newAppointment.getMedicalStaffId() > 0;
            setMedicalStaffId(newAppointment.getMedicalStaffId());
        }
    }

    public static Appointment mergeAppointmentData(Appointment oldAppointment, Appointment newAppointment) {
        Appointment tempAppointment = new Appointment();
        tempAppointment.mergeAppointmentData(oldAppointment);
        tempAppointment.mergeAppointmentData(newAppointment);
        return tempAppointment;
    }

    public String toString() {
        return "Appointment Id: " + appointmentId + " " + getDateTimeString(dateTimeCode) + " Patient ID: " + patientId
                + " Staff ID: " + medicalStaffId;
    }
}
