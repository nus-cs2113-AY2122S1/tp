package medbot.person;

public class Staff extends Person {

    public Staff() {
        super.personType = PersonType.STAFF;
    }

    @Override
    public String toString() {
        return "Staff ID: " + getId() + " " + super.toString();
    }

}
