package medbot.list;

import java.util.HashMap;
import medbot.Appointment;
import medbot.exceptions.MedBotException;

public class SchedulerAppointmentList {
    private static final String END_LINE = System.lineSeparator();

    protected HashMap<Integer, Appointment> appointments = new HashMap<>();
    private int lastId = 1;

    public SchedulerAppointmentList() {

    }

    private int generateAppointmentId() {
        while (appointments.containsKey(lastId)) {
            lastId++;
        }
        return lastId;
    }

    /**
     * Returns a copy of the appointment with the specified appointmentId.
     *
     * @param appointmentId the appointmentId to search for
     * @return copy of appointment with the specified appointmentId
     * @throws MedBotException if no appointment with the specified appointmentId is found
     */
    public Appointment getAppointment(int appointmentId) throws MedBotException {
        if (!appointments.containsKey(appointmentId)) {
            throw new MedBotException(getAppointmentNotFoundErrorMessage(appointmentId));
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentId);
        appointment.mergeAppointmentData(appointments.get(appointmentId));
        return appointment;
    }

    public int addAppointment(Appointment appointment) throws MedBotException {
        assert appointment.isComplete();
        int appointmentId = appointment.getAppointmentId();
        if (appointments.containsKey(appointmentId)) {
            throw new MedBotException("Appointment with id " + appointmentId + " already exits!");
        }
        if (appointmentId == 0) {
            appointmentId = generateAppointmentId();
            appointment.setAppointmentId(appointmentId);
        }
        appointments.put(appointmentId, appointment);
        return appointmentId;
    }

    public boolean doesAppointmentExist(int appointmentId) {
        return appointments.containsKey(appointmentId);
    }

    public Appointment deleteAppointment(int appointmentId) throws MedBotException {
        if (!appointments.containsKey(appointmentId)) {
            throw new MedBotException(getAppointmentNotFoundErrorMessage(appointmentId));
        }
        return appointments.remove(appointmentId);
    }

    private String getAppointmentNotFoundErrorMessage(int appointmentId) {
        return "No appointment with ID " + appointmentId + " found.";
    }

    public String listAppointments() {
        String output = "";
        for (int appointmentId : appointments.keySet()) {
            output += appointments.get(appointmentId) + END_LINE;
        }
        return output;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
}
