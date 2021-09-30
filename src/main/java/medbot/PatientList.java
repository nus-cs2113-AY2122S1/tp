package medbot;

import java.util.HashMap;
import medbot.person.Patient;

public class PatientList {
    private HashMap<Integer, Patient> patients = new HashMap<>();
    private int lastId = 1;

    public int size() {
        return patients.size();
    }

    public int addPatient(Patient patient) {
        int patientId = generatePatientId();
        patient.setPatientId(patientId);
        patients.put(patientId, patient);
        return patientId;
    }

    public String getPatientInfo(int patientId) throws MedBotException {
        if (!patients.containsKey(patientId)) {
            throw new MedBotException("No patient with ID " + patientId + " found.");
        }
        return patients.get(patientId).toString();
    }

    private int generatePatientId() {
        while (patients.containsKey(lastId)) {
            lastId++;
        }
        return lastId;
    }

    public Patient deletePatient(int patientId) throws MedBotException {
        if (!patients.containsKey(patientId)) {
            throw new MedBotException("No patient with ID " + patientId + " found.");
        }
        return patients.remove(patientId);
    }

    public void listPatients() {
        for (int key : patients.keySet()) {
            System.out.println(patients.get(key));
        }
    }
}
