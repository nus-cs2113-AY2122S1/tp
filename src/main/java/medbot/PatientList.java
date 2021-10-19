package medbot;

import medbot.exceptions.MedBotException;
import medbot.person.Patient;

import java.util.List;

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

    public List<String> findPatients(String[] parameters) throws MedBotException {
        return super.findPersons(parameters);
    }

    public String listPatients() {
        return super.listPersons();
    }

    private String getNoPatientIdErrorMessage(int patientId) {
        return "No Patient with ID " + super.getNoPersonIdErrorMessage(patientId);
    }

    public void addPatientFromStorage(Patient patient) {
        super.addPersonFromStorage(patient);
    }
}