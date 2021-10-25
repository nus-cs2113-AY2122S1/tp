package medbot.list;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import medbot.Appointment;
import medbot.exceptions.MedBotException;

public class PersonalAppointmentList {
    private static final String ERROR_APPOINTMENT_ID_NOT_SET = "Appointment ID is not set.";
    private static final String ERROR_ADD_APPOINTMENT_CLASH = "New appointment clashes with another appointment.";
    private static final String END_LINE = System.lineSeparator();

    private final NavigableSet<Appointment> appointments = new TreeSet<>((o1, o2) -> {
        if (o1.getDateTimeCode() > o2.getDateTimeCode()) {
            return 1;
        } else if (o1.getDateTimeCode() < o2.getDateTimeCode()) {
            return -1;
        }
        assert o1.getDateTimeCode() == o2.getDateTimeCode();
        return 0;
    });

    public PersonalAppointmentList() {

    }

    /**
     * Adds the given appointment to the appointment list.
     *
     * @param appointment Appointment to be added to the appointment list
     * @throws MedBotException if there is another appointment at that time
     */
    public void addAppointment(Appointment appointment) throws MedBotException {
        int appointmentId = appointment.getAppointmentId();
        if (appointmentId == 0) {
            throw new MedBotException(ERROR_APPOINTMENT_ID_NOT_SET);
        }
        boolean isNotClash = appointments.add(appointment);
        if (!isNotClash) {
            throw new MedBotException(ERROR_ADD_APPOINTMENT_CLASH);
        }

    }

    /**
     * Returns the appointmentId of the appointment at the specified time code, or -1 if there is none.
     *
     * @param dateTimeCode the dateTimeCode to search for
     * @return the appointmentId of the appointment with that dateTimeCode
     */
    public int getAppointmentId(int dateTimeCode) {
        for (Appointment appointment : appointments) {
            if (appointment != null && appointment.getDateTimeCode() == dateTimeCode) {
                return appointment.getAppointmentId();
            }
        }
        return -1;
    }

    /**
     * Removes the appointment with the specified dateTimeCode.
     *
     * @param dateTimeCode the dateTimeCode of the appointment to be deleted
     * @throws MedBotException if there is no appointment with that dateTimeCode.
     */
    public void deleteAppointment(int dateTimeCode) throws MedBotException {
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

    private String getAppointmentNotFoundErrorMessage(int dateTimeCode) {
        return "No appointment at : " + Appointment.getDateTimeString(dateTimeCode) + "found.";
    }

    public String listAppointments() {
        String output = "";
        for (Appointment appointment : appointments) {
            output += appointment + END_LINE;
        }
        return output;
    }

    public String listAppointmentsAfter(int dateTimeCode) {
        Appointment referenceAppointment = new Appointment();
        referenceAppointment.setDateTimeCode(dateTimeCode);
        NavigableSet<Appointment> appointmentsAfter = appointments.tailSet(referenceAppointment, true);
        String output = "";
        for (Appointment appointment : appointmentsAfter) {
            output += appointment + END_LINE;
        }
        return output;
    }

    public String listAppointmentsBefore(int dateTimeCode) {
        Appointment referenceAppointment = new Appointment();
        referenceAppointment.setDateTimeCode(dateTimeCode);
        NavigableSet<Appointment> appointmentsBefore = appointments.headSet(referenceAppointment, true);
        String output = "";
        for (Appointment appointment : appointmentsBefore) {
            output += appointment + END_LINE;
        }
        return output;
    }

}
