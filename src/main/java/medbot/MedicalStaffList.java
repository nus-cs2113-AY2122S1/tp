package medbot;

import medbot.exceptions.MedBotException;
import medbot.person.Patient;
import medbot.person.Staff;

public class MedicalStaffList extends PersonList {

    public int addMedicalStaff(Patient patient) {
        return super.addPerson(patient);
    }

    public String getMedicalStaffInfo(int staffId) throws MedBotException {
        return super.getPersonInfo(staffId);
    }

    public void editMedicalStaff(int staffId, Staff newStaffData) throws MedBotException {
        super.editPerson(staffId, newStaffData);
    }

    public void deleteMedicalStaff(int staffId) throws MedBotException {
        super.deletePerson(staffId);
    }

    public String listMedicalStaff() {
        return super.listPersons();
    }

    public void addPatientFromStorage(Patient patient) {
        super.addPersonFromStorage(patient);
    }
}
