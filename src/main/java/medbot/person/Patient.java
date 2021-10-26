package medbot.person;

import static medbot.ui.Ui.VERTICAL_LINE_SPACED;

public class Patient extends Person {

    public Patient() {
        super.personType = PersonType.PATIENT;
    }

    public String toString() {
        return "Patient ID: " + getListItemId() + " " + super.toString();
    }

    /**
     * Text to be written to storage file of a patient.
     *
     * @return storageString of a patient
     */
    @Override
    public String getStorageString() {
        return getListItemId() + VERTICAL_LINE_SPACED
                + super.getStorageString();
    }
}
