package medbot.list;

import medbot.exceptions.MedBotException;
import medbot.person.Staff;

public class MedicalStaffList extends PersonList {

    public int addPerson(Staff staff) {
        return super.addPerson(staff);
    }

    public String getPersonInfo(int staffId) throws MedBotException {
        return super.getPersonInfo(staffId);
    }

    public void editPerson(int staffId, Staff newStaffData) throws MedBotException {
        super.editPerson(staffId, newStaffData);
    }

    public void deletePerson(int staffId) throws MedBotException {
        super.deletePerson(staffId);
    }

    public String listMedicalStaff() {
        return super.listPersons();
    }

    public void addPersonFromStorage(Staff staff) {
        super.addPersonFromStorage(staff);
    }
}
