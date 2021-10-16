package medbot;

import medbot.person.Patient;
import medbot.person.Staff;

public class StaffList extends PersonList {

    public int addStaff(Patient patient) {
        return super.addPerson(patient);
    }

    public String getStaffInfo(int staffId) throws MedBotException {
        return super.getPersonInfo(staffId);
    }

    public void editStaff(int staffId, Staff newStaffData) throws MedBotException {
        super.editPerson(staffId, newStaffData);
    }

    public void deletePatient(int staffId) throws MedBotException {
        super.deletePerson(staffId);
    }

    public String listStaff() {
        return super.listPersons();
    }

    public void addPatientFromStorage(Patient patient) {
        super.addPersonFromStorage(patient);
    }
}
