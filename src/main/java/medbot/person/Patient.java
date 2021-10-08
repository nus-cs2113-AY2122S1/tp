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


    @Override
    public String getStorageString() {
        return patientId + DATA_SEPARATOR_UNESCAPED +
                super.getStorageString();
    }
}
