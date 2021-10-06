package medbot;

import medbot.person.Patient;

import java.util.HashMap;

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
            throw new MedBotException(getNoPatientIdErrorMessage(patientId));
        }
        return patients.get(patientId).toString();
    }

    private int generatePatientId() {
        while (patients.containsKey(lastId)) {
            lastId++;
        }
        return lastId;
    }

    /**
     * Replaces all values of the Patient data that is non-null in the new inputted data.
     *
     * @param oldPatientData the old patient data in the system
     * @param newPatientData the new patient data inputted by the user
     */
    private void mergeEditPatientData(Patient oldPatientData, Patient newPatientData) {
        if (newPatientData.getName() != null) {
            oldPatientData.setName(newPatientData.getName());
        }
        if (newPatientData.getIcNumber() != null) {
            oldPatientData.setIcNumber(newPatientData.getIcNumber());
        }
        if (newPatientData.getEmailAddress() != null) {
            oldPatientData.setEmailAddress(newPatientData.getEmailAddress());
        }
        if (newPatientData.getPhoneNumber() != null) {
            oldPatientData.setPhoneNumber(newPatientData.getPhoneNumber());
        }
        if (newPatientData.getResidentialAddress() != null) {
            oldPatientData.setResidentialAddress(newPatientData.getResidentialAddress());
        }
    }

    /**
     * Edits the specified fields on patient information with new values from the user.
     *
     * @param patientId      The patient with information to change
     * @param newPatientData the new Patient data to change to (except the null fields)
     * @throws MedBotException when the patient ID cannot be found
     */
    public void editPatient(int patientId, Patient newPatientData) throws MedBotException {
        if (!patients.containsKey(patientId)) {
            throw new MedBotException(getNoPatientIdErrorMessage(patientId));
        } else {
            mergeEditPatientData(patients.get(patientId), newPatientData);
        }
    }

    public Patient deletePatient(int patientId) throws MedBotException {
        if (!patients.containsKey(patientId)) {
            throw new MedBotException(getNoPatientIdErrorMessage(patientId));
        }
        return patients.remove(patientId);
    }

    public void listPatients() {
        for (int key : patients.keySet()) {
            System.out.println(patients.get(key));
        }
    }

    private String getNoPatientIdErrorMessage(int patientId) {
        return "No patient with ID " + patientId + " found.";
    }
}
