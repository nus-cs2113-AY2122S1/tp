package medbot.list;

import medbot.Appointment;
import medbot.exceptions.MedBotException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

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
        int appointmentId = appointment.getId();
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
                return appointment.getId();
            }
        }
        return -1;
    }

    /**
     * Returns a LinkedList of the appointmentId of all appointments.
     *
     * @return LinkedList of the appointmentId of all appointments
     */
    public LinkedList<Integer> getAllAppointmentIds() {
        LinkedList<Integer> appointmentIds = new LinkedList<>();

        for (Appointment appointment : appointments) {
            if (appointment != null) {
                appointmentIds.add(appointment.getId());
            }
        }
        return appointmentIds;
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

    /**
     * Returns the error message when no appointment was found.
     *
     * @param dateTimeCode the dateTimeCode to look for.
     * @return the String indicates the error message.
     */
    private String getAppointmentNotFoundErrorMessage(int dateTimeCode) {
        return "No appointment at : " + Appointment.formatDateTimeCode(dateTimeCode) + "found.";
    }

    public List<Integer> listAppointments() {
        List<Integer> appointmentIds = new LinkedList<>();
        for (Appointment appointment : appointments) {
            appointmentIds.add(appointment.getId());
        }
        return appointmentIds;
    }

    /**
     * Returns the list of appointment after the date time indicated.
     *
     * @param dateTimeCode the date time of the appointments to look for.
     * @returnThe list of appointments after the date.
     */
    public List<Integer> listAppointmentsAfter(int dateTimeCode) {
        Appointment referenceAppointment = new Appointment();
        referenceAppointment.setDateTimeCode(dateTimeCode);
        NavigableSet<Appointment> appointmentsAfter = appointments.tailSet(referenceAppointment, true);
        List<Integer> appointmentIds = new LinkedList<>();
        for (Appointment appointment : appointmentsAfter) {
            appointmentIds.add(appointment.getId());
        }
        return appointmentIds;
    }

    /**
     * Returns the list of appointment before the date time indicated.
     *
     * @param dateTimeCode the date time of the appointments to look for.
     * @returnThe list of appointments before the date.
     */
    public List<Integer> listAppointmentsBefore(int dateTimeCode) {
        Appointment referenceAppointment = new Appointment();
        referenceAppointment.setDateTimeCode(dateTimeCode);
        NavigableSet<Appointment> appointmentsBefore = appointments.headSet(referenceAppointment, true);
        List<Integer> appointmentIds = new LinkedList<>();
        for (Appointment appointment : appointmentsBefore) {
            appointmentIds.add(appointment.getId());
        }
        return appointmentIds;
    }

}
