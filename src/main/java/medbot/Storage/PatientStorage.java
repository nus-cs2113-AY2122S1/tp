package medbot.Storage;

import medbot.exceptions.MedBotException;


public class PatientStorage extends PersonStorage {

    private static final String PATIENT_DATA_PATH = "storage/patient.txt";

    public PatientStorage() throws MedBotException {
        super(PATIENT_DATA_PATH);
    }


}
