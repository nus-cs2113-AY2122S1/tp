package medbot.storage;

import medbot.exceptions.MedBotException;


public class PatientStorage extends PersonStorage {

    private static final String PATIENT_DATA_PATH = "MedBotData/patient.txt";

    /**
     * Instantiate storage at the default path, PATIENT_DATA_PATH.
     *
     * @throws MedBotException if unable to create/detect storage file
     */
    public PatientStorage() throws MedBotException {
        super(PATIENT_DATA_PATH);
    }

    /**
     * For testing purposes, using a custom data path.
     *
     * @param testDataPath custom data path
     * @throws MedBotException if unable to create/detect storage file
     */
    protected PatientStorage(String testDataPath) throws MedBotException {
        super(testDataPath);
    }


}
