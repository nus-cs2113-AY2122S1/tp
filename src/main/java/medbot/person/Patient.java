package medbot.person;

public class Patient extends Person {

    public Patient() {
        super.personType = PersonType.PATIENT;
    }

    public String toString() {
        return "Patient ID: " + getId() + super.toString();
    }

}
