package medbot;


import medbot.list.ListItem;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static medbot.ui.Ui.VERTICAL_LINE_SPACED;


public class Appointment implements ListItem {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yy HH00");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_STORAGE = DateTimeFormatter.ofPattern("ddMMyy HH00");

    private int appointmentId = 0;
    private int patientId = 0;
    private int medicalStaffId = 0;
    private int dateTimeCode = 0;

    public int getId() {
        return appointmentId;
    }

    public void setId(int appointmentId) {
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

    public String getDateTimeString() {
        return formatDateTimeCode(dateTimeCode);
    }

    public static String formatDateTimeCode(int dateTimeCode) {
        long epochSecond = (long) dateTimeCode * 60;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecond, 0, ZONE_OFFSET);
        return localDateTime.format(DATE_TIME_FORMATTER) + "HRS";
    }

    /**
     * Returns whether appointment is complete. I.e., contains a positive patientId, medicalStaffId and dateTimeCode.
     *
     * @return whether appointment is complete
     */
    public boolean isComplete() {
        return patientId > 0 && medicalStaffId > 0 && dateTimeCode > 0;
    }

    /**
     * Updates parameters (patientId, medicalStaffId and dateTimeCode) with non-zero parameters of newAppointment.
     *
     * <p>Does not update appointmentId.
     *
     * @param newAppointment Appointment object that contains the parameters to be updated
     */
    public void mergeAppointmentData(Appointment newAppointment) {
        if (newAppointment.getDateTimeCode() > 0) {
            setDateTimeCode(newAppointment.getDateTimeCode());
        }
        if (newAppointment.getPatientId() > 0) {
            setPatientId(newAppointment.getPatientId());
        }
        if (newAppointment.getMedicalStaffId() > 0) {
            setMedicalStaffId(newAppointment.getMedicalStaffId());
        }
    }

    /**
     * Updates parameters (patientId, medicalStaffId and dateTimeCode) of a copy of oldAppointment
     * with non-zero parameters of newAppointment.
     *
     * <p>Does not update appointmentId.
     *
     * @param oldAppointment Appointment to be updated.
     * @param newAppointment Appointment that contains the parameters to be updated
     * @return a copy of oldAppointment updated with the attributes in newAppointment
     */
    public static Appointment mergeAppointmentData(Appointment oldAppointment, Appointment newAppointment) {
        Appointment tempAppointment = new Appointment();
        tempAppointment.mergeAppointmentData(oldAppointment);
        tempAppointment.mergeAppointmentData(newAppointment);
        return tempAppointment;
    }

    public String toString() {
        return "Appointment Id: " + appointmentId + " Date/Time: " + getDateTimeString() + " Patient ID: "
                + patientId + " Staff ID: " + medicalStaffId + "\n";
    }

    /**
     * Text to be written to storage file of a person.
     *
     * @return storageString of a person
     */
    public String getStorageString() {
        return appointmentId + VERTICAL_LINE_SPACED
                + getDateTimeStorageString(dateTimeCode) + VERTICAL_LINE_SPACED
                + patientId + VERTICAL_LINE_SPACED
                + medicalStaffId;
    }

    /**
     * Converts dateTimeCode to a String in the format of ddMMyy HH00.
     *
     * @param dateTimeCode date time code
     * @return returns a string of the date in the format of ddMMyy HH00
     */
    private String getDateTimeStorageString(int dateTimeCode) {
        long epochSecond = (long) dateTimeCode * 60;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecond, 0, ZONE_OFFSET);
        return localDateTime.format(DATE_TIME_FORMATTER_STORAGE);
    }

}
