package medbot.storagetests;

import medbot.exceptions.MedBotException;
import medbot.storage.StaffStorage;

public class StaffStorageStub extends StaffStorage {

    public StaffStorageStub(String testDataPath) throws MedBotException {
        super(testDataPath);
    }
}
