package medbot.person;

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
}
