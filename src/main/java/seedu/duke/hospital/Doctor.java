package seedu.duke.hospital;

public class Doctor {

    private final String name;
    private final Integer number;

    public Doctor(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Returns the doctor's name.
     *
     * @return Name of the doctor.
     */
    public String getDoctorName() {
        return name;
    }

    /**
     * Returns the doctor's number.
     *
     * @return Number of the doctor.
     */
    public Integer getDoctorNumber() {
        return number;
    }

    public String toString() {
        return String.format("%s (Doctor Name)\n%s (Doctor Number)", getDoctorName(), getDoctorNumber());
    }
}
