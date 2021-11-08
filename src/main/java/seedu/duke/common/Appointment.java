package seedu.duke.common;

public class Appointment {

    private final String location;
    private final String date;
    private final String time;
    private final String purpose;

    public Appointment(String location, String date, String time, String purpose) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.purpose = purpose;
    }

    /**
     * Returns the appointment's location.
     *
     * @return Location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the appointment's date.
     *
     * @return Date of the appointment.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the appointment's time.
     *
     * @return Time of the appointment.
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the appointment's purpose.
     *
     * @return Purpose of the appointment.
     */
    public String getPurpose() {
        return purpose;
    }

    @Override
    public String toString() {
        return String.format("Location: %s, Date: %s, Time: %s, Purpose of Visit: %s", getLocation(), getDate(),
                getTime(), getPurpose());
    }
}
