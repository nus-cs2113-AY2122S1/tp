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
        }
        assert (patientId > 0) && (patientId <= size());
        mergeEditPatientData(patients.get(patientId), newPatientData);
    }

    /**
     * Deletes the specified patient.
     *
     * @param patientId The patient to delete.
     * @throws MedBotException when the patient ID cannot be found.
     */
    public void deletePatient(int patientId) throws MedBotException {
        if (!patients.containsKey(patientId)) {
            throw new MedBotException(getNoPatientIdErrorMessage(patientId));
        }
        assert (patientId > 0) && (patientId <= size());
        patients.remove(patientId);
    }

    /**
     * Lists all current patients.
     *
     * @return String displays the list of all current patients
     */
    public String listPatients() {
        String output = "";
        for (int key : patients.keySet()) {
            output += patients.get(key);
            output += "\n";
        }
        return output;
    }

    private String getNoPatientIdErrorMessage(int patientId) {
        return "No patient with ID " + patientId + " found.";
    }

    /**
     * Get storageString for all patients.
     *
     * @return storageString of all patients
     */
    public String getStorageString() {
        String output = "";
        for (int key : patients.keySet()) {
            Patient patient = patients.get(key);
            String patientStorageString = patient.getStorageString();
            output += patientStorageString + "\n";
        }
        return output;
    }

    /**
     * Adds a patient to patients hashmap.
     *
     * @param patient an instance of Patient
     */
    public void addPatientFromStorage(Patient patient) {
        int patientId = patient.getPatientId();
        patients.put(patientId, patient);
    }

    /**
     * Set lastId to a new number.
     *
     * @param newLastId lastId to be set to this
     */
    public void setLastId(int newLastId) {
        lastId = newLastId;
    }
}
