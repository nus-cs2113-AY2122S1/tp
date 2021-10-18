package medbot.person;

public class Staff extends Person {

    public Staff() {
        super.personType = PersonType.STAFF;
    }

    public int getStaffId() {
        return super.getPersonId();
    }

    public void setStaffId(int staffId) {
        super.setPersonId(staffId);
    }

    public String toString() {
        return "Staff ID: " + getStaffId() + " " + super.toString();
    }
}
