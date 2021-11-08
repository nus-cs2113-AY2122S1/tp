package seedu.duke.common;

public class Medicine {

    private final String medicineName;
    private final String frequency;

    public Medicine(String medicineName, String frequency) {
        this.medicineName = medicineName;
        this.frequency = frequency;
    }

    /**
     * Returns the medicine's name.
     *
     * @return Name of the medicine.
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Returns the medicine's frequency.
     *
     * @return Frequency of the medicine.
     */
    public String getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return String.format("Medicine Name: %s, Frequency: %s", getMedicineName(), getFrequency());
    }

}

