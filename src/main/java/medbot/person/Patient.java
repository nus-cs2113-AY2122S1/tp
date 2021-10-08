package medbot.person;

import static medbot.Ui.DATA_SEPARATOR_UNESCAPED;

public class Patient extends Person {

    private int patientId = 0;

    public Patient() {
        super.personType = PersonType.PATIENT;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String toString() {
        return "Patient ID: " + patientId + " " + super.toString();
    }


    /**
     * Text to be written to storage/data.txt of a patient
     *
     * @return storageString of a patient
     */
    @Override
    public String getStorageString() {
        return patientId + DATA_SEPARATOR_UNESCAPED +
                super.getStorageString();
    }
}
