package medbot.list;

import java.time.LocalDateTime;
import java.util.HashMap;
import medbot.Appointment;
import medbot.exceptions.MedBotException;

public class PersonalAppointmentList {
    private static final String ERROR_APPOINTMENT_ID_NOT_SET = "Appointment ID is not set.";
    private static final String ERROR_ADD_APPOINTMENT_CLASH = "New appointment clashes with another appointment.";
    private static final String ERROR_EDIT_APPOINTMENT_CLASH = "New appointment time clashes with another appointment.";

    protected HashMap<Integer, Appointment> appointments = new HashMap<>();

    public PersonalAppointmentList() {

    }

    public void addAppointment(Appointment appointment) throws MedBotException {
        int appointmentId = appointment.getAppointmentId();
        int dateTimeCode = appointment.getDateTimeCode();
        if (appointmentId == 0) {
            throw new MedBotException(ERROR_APPOINTMENT_ID_NOT_SET);
        }
        if (!appointments.containsKey(dateTimeCode)) {
            appointments.put(dateTimeCode, appointment);
            return;
        }
        throw new MedBotException(ERROR_ADD_APPOINTMENT_CLASH);
    }

    public int getAppointmentId(int dateTimeCode) {
        Appointment appointment = appointments.get(dateTimeCode);
        if (appointment != null) {
            return appointment.getAppointmentId();
        }
        return -1;
    }

    public void deleteAppointmentByTime(int dateTimeCode) throws MedBotException {
        if (!appointments.containsKey(dateTimeCode)) {
            throw new MedBotException(getAppointmentNotFoundErrorMessage(dateTimeCode));
        }
        appointments.remove(dateTimeCode);
    }

    private void mergeAppointmentDataByTime(Appointment oldAppointment, Appointment newAppointment) {
        if (oldAppointment.getDateTimeCode() != newAppointment.getDateTimeCode()
                && newAppointment.getDateTimeCode() == 0) {
            oldAppointment.mergeAppointmentData(newAppointment);
        }

    }

    private String getAppointmentNotFoundErrorMessage(int dateTimeCode) {
        return "No appointment at : " + Appointment.getDateTimeString(dateTimeCode) + "found.";
    }

    public String listAppointments() {
        String output = "";
        for (int dateTimeCode:appointments.keySet()) {
            output += appointments.get(dateTimeCode);
            output += "\n";
        }
        return output;
    }
}
