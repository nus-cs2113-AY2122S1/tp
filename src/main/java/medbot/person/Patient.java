package medbot.person;

import static medbot.ui.Ui.VERTICAL_LINE_SPACED;

public class Patient extends Person {

    public Patient() {
        super.personType = PersonType.PATIENT;
    }

    public String toString() {
        return "Patient ID: " + getPersonId() + " " + super.toString();
    }

    /**
     * Text to be written to storage file of a patient
     *
     * @return storageString of a patient
     */
    @Override
    public String getStorageString() {
        return getPersonId() + VERTICAL_LINE_SPACED
                + super.getStorageString();
    }
}
