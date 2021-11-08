package medbot.storagetests;

import medbot.exceptions.MedBotException;
import medbot.storage.PatientStorage;

public class PatientStorageStub extends PatientStorage {

    public PatientStorageStub(String testDataPath) throws MedBotException {
        super(testDataPath);
    }
}
