package medbot.list;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import medbot.Appointment;
import medbot.exceptions.MedBotException;

public class PersonalAppointmentList {
    private static final String ERROR_APPOINTMENT_ID_NOT_SET = "Appointment ID is not set.";
    private static final String ERROR_ADD_APPOINTMENT_CLASH = "New appointment clashes with another appointment.";
    private static final String ERROR_EDIT_APPOINTMENT_CLASH = "New appointment time clashes with another appointment.";

    protected NavigableSet<Appointment> appointments = new TreeSet<>((o1, o2) -> {
        if (o1.getDateTimeCode() > o2.getDateTimeCode()) {
            return 1;
        } else if ( o1.getDateTimeCode() < o2.getDateTimeCode()) {
            return -1;
        }
        assert o1.getDateTimeCode() == o2.getDateTimeCode();
        return 0;
    });

    public PersonalAppointmentList() {

    }

    public void addAppointment(Appointment appointment) throws MedBotException {
        int appointmentId = appointment.getAppointmentId();
        int dateTimeCode = appointment.getDateTimeCode();
        if (appointmentId == 0) {
            throw new MedBotException(ERROR_APPOINTMENT_ID_NOT_SET);
        }
        boolean isNotClash = appointments.add(appointment);
        if (!isNotClash) {
            throw new MedBotException(ERROR_ADD_APPOINTMENT_CLASH);
        }

    }

    public int getAppointmentId(int dateTimeCode) {
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment appointment = it.next();
            if (appointment.getDateTimeCode() == dateTimeCode && appointment != null) {
                return appointment.getAppointmentId();
            }
        }
        return -1;
    }

    public void deleteAppointmentByTime(int dateTimeCode) throws MedBotException {
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment appointment = it.next();
            if (appointment.getDateTimeCode() == dateTimeCode) {
                it.remove();
                return;
            }
        }
        throw new MedBotException(getAppointmentNotFoundErrorMessage(dateTimeCode));
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
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            output += it.next();
            output += "\n";
        }
        return output;
    }
}
