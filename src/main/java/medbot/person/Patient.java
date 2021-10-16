package medbot.person;

import static medbot.Ui.VERTICAL_LINE_SPACED;

public class Patient extends Person {

    public Patient() {
        super.personType = PersonType.PATIENT;
    }

    public int getPatientId() {
        return super.getPersonId();
    }

    public void setPatientId(int patientId) {
        super.setPersonId(patientId);
    }

    public String toString() {
        return "Patient ID: " + getPatientId() + " " + super.toString();
    }


    /**
     * Text to be written to storage/data.txt of a patient
     *
     * @return storageString of a patient
     */
    @Override
    public String getStorageString() {
        return getPatientId() + VERTICAL_LINE_SPACED
                + super.getStorageString();
    }
}
