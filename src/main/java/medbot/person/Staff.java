package medbot.person;

import static medbot.ui.Ui.VERTICAL_LINE_SPACED;

public class Staff extends Person {

    public Staff() {
        super.personType = PersonType.STAFF;
    }

    @Override
    public String toString() {
        return "Staff ID: " + getPersonId() + " " + super.toString();
    }

    /**
     * Text to be written to storage/data.txt of a patient
     *
     * @return storageString of a patient
     */
    @Override
    public String getStorageString() {
        return getPersonId() + VERTICAL_LINE_SPACED
                + super.getStorageString();
    }
}
