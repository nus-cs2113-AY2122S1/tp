package medbot.storage;

import medbot.exceptions.MedBotException;


public class PatientStorage extends PersonStorage {

    private static final String PATIENT_DATA_PATH = "MedbotData/patient.txt";

    public PatientStorage() throws MedBotException {
        super(PATIENT_DATA_PATH);
    }


}
