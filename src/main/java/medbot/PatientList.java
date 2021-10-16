package medbot;

import medbot.person.Patient;

public class PatientList extends PersonList {

    public int addPatient(Patient patient) {
        return super.addPerson(patient);
    }

    public String getPatientInfo(int patientId) throws MedBotException {
        return super.getPersonInfo(patientId);
    }

    public void editPatient(int patientId, Patient newPatientData) throws MedBotException {
        super.editPerson(patientId, newPatientData);
    }

    public void deletePatient(int patientId) throws MedBotException {
        super.deletePerson(patientId);
    }

    public String listPatients() {
        return super.listPersons();
    }

    public void addPatientFromStorage(Patient patient) {
        super.addPersonFromStorage(patient);
    }
}